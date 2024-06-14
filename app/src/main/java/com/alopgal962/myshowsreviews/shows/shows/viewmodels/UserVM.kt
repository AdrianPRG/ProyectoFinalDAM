package com.alopgal962.myshowsreviews.shows.shows.viewmodels

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alopgal962.myshowsreviews.shows.shows.data.model.User
import com.alopgal962.myshowsreviews.shows.shows.ui.state.ShowState
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.firestore.toObjects
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@SuppressLint("MutableCollectionMutableState")
/**
 * @property _user variable privada que guarda el estado del usuario, al cual se le asignarán los datos una vez se inicie sesion
 * @property user Observa el estado de _user. Es la variable que se observar en la UI
 * @property _showInsertar variable que contendrá el estado de el show que queramos insertar. Este es el que aparecerá en la pantalla
 * AddShow a la hora de añadirlo. Ademas, es en el que se aplicaran los cambios en el viewmodel.
 * @property showInsertar observa el estado de _showInsertar.
 * @property _showEstadisticas variable privada que guarda el estado del show del que se desea observar las estadisticas. En este show se guarda
 * la pelicula que aparecerá en la pantalla MyFullShowScreen
 * @property imagenRegister guarda la cadena de la imagen que elige el usuario al registrarse. Este se guardará en la BD al registrarse.
 * @property nombreRegister guarda el nombre que el usuario elige. Este se guardará en la BD al registrarse.
 * @property emaiLRegisterLogin guarda el correo electronico del usuario. Este se guardará en la BD al registrarse.
 * @property passwordRegisterLogin guarda la contraseña de usuario. Este se guardará en la BD al registrarse.
 * @property listaSeries contiene la lista de series. Esta se crea para ser asignada a la coleccion usuarios. Este se guardará en la BD al registrarse.
 * @property listanombres contiene una lista de los nombres de los usuarios de la DB. Para comprobar si el nombre del usuario ya existe en la BD.
 * @property _desc variable privada que guarda un booleano. Al pulsarse sobre un botón cambiara a true o false. Esto decidirá si se muestra en la pantalla la descripcion
 * de la serie o la reseña del usuario (Pantalla MyFullShowScreen).
 * @property desc guarda el estado de la variable _desc.
 * @property _sorted variable privada que guarda un string, el cual decidirá como se muestran las series del usuario en pantalla:
 *      -ordenada de mayor a menor : alta
 *      -ordenada de menor a mayor: baja
 *      -normal: normal
 * @property sort guarda el estado de _sorted
 * @property resena guarda el estado de la reseña que el usuario introducira en la serie. Se le asignará al show a la hora de crearlo
 * @property puntuacion guarda el estado de la puntuacion que el usuario le dará a la serie. Se le asignará al show a la hora de crearlo
 * @property listaUsuarios guarda la lista de usuarios. La obtiene de la BD.
 * @property VMFireDB contiene la instancia del modulo de base de datos de firebase
 * @property VMFireAuth contiene la instancia del modulo de autorizacion de firebase.
 */
class UserVM : ViewModel() {


    private var _user = MutableStateFlow(User())

    var user = _user.asStateFlow()

    private var _showInsertar = MutableStateFlow(ShowState())

    var showInsertar = _showInsertar.asStateFlow()

    private var _showEstadisticas = MutableStateFlow(ShowState())

    var showEstadisticas = _showEstadisticas.asStateFlow()

    var imagenRegister by mutableStateOf("")
    var nombreRegister by mutableStateOf("")
    var emaiLRegisterLogin by mutableStateOf("")
    var passwordRegisterLogin by mutableStateOf("")

    var listaSeries: MutableList<ShowState> = mutableListOf()

    var listanombres = mutableListOf<String>()

    private var _desc = MutableStateFlow(true)

    var desc = _desc.asStateFlow()

   private var _sorted = MutableStateFlow("medio")

    var sort = _sorted.asStateFlow()

    var resena by mutableStateOf("")

    var puntuacion by mutableStateOf("")

    var puntuacionmedia by mutableStateOf("")

    var listaUsuarios by mutableStateOf(mutableListOf<User>())

    var VMFireAuth = Firebase.auth

    var VMFireDB = Firebase.firestore


    /**
     * Se ejecuta en corrutina y dentro de un bloque try-catch
     *Lo primero,se obtienen los nombres de usuarios de la base de datos
     * Se comprueba que ninguno de los campos son vacios y que cumplen las condiciones
     * Si estos cumplen las condiciones,se llama a la funcion de crear usuario en el modulo de autorizacion de firebase y a continuacion se crea un nuevo documento con los campos introducidos
     * por el usuario (imagen,nombre,etc), creandose asi un nuevo usuario en la BD.
     * Una vez se ejecuta, se llama a la funcion lambda pasada por parametros y se borran los campos de registro/login (nombre,email,etc).
     * @param navegacion se ejecutará si alfinal todo ha sido exitoso
     */
    fun registrarme(navegacion: () -> Unit,context: Context) {
        viewModelScope.launch {
            try {
                obtenerNombres()
                if (imagenRegister != "" && nombreRegister.length>0 && nombreRegister.length<=10 && emaiLRegisterLogin != "" && passwordRegisterLogin.length >= 6 && !listanombres.contains(nombreRegister)) {
                    VMFireAuth.createUserWithEmailAndPassword(
                        emaiLRegisterLogin,
                        passwordRegisterLogin
                    ).addOnCompleteListener {
                        if (it.isSuccessful) {
                            VMFireDB.collection("Usuarios").document(emaiLRegisterLogin).set(
                                hashMapOf(
                                    "image" to imagenRegister,
                                    "nombre" to nombreRegister,
                                    "email" to emaiLRegisterLogin,
                                    "password" to passwordRegisterLogin,
                                    "listaSeries" to listaSeries,
                                )
                            ).addOnCompleteListener {
                                navegacion()
                                borrarCamposRegistroLogin()
                            }
                        }
                    }
                }
                else{
                    Toast.makeText(context,"¡Uno de los campos no es correcto!",Toast.LENGTH_LONG).show()
                }
            } catch (e: Exception) {
                Log.d("ERRORREGISTER", "No se pudo registrar")
            }
        }
    }

    /**
     * Comprueba que el correo y la contraseña introducidos no nulos.
     * Si es correcto, llama a la funcion de inicio de sesion de firebase.
     * Una vez iniciada sesion, obtiene de la BD (Collecion usuarios) el documento del correo electronico que se ha introducido,
     * obteniendo asi los datos de usuario del correo introducido.
     * Una vez se crea el usuario con los datos obtenidos, se ejecuta la funcion lambda
     * @param navegacion se ejecutará una vez se inicie sesion y el usuario haya sido creado
     */
    fun iniciarsesion(navegacion: () -> Unit,context: Context) {
        viewModelScope.launch {
            try {
                if (emaiLRegisterLogin.isNotEmpty() && passwordRegisterLogin.isNotEmpty()) {
                    VMFireAuth.signInWithEmailAndPassword(emaiLRegisterLogin, passwordRegisterLogin)
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                VMFireDB.collection("Usuarios").document(emaiLRegisterLogin).get()
                                    .addOnSuccessListener {
                                        _user.value = it.toObject<User>()!!
                                        navegacion()
                                    }.addOnFailureListener {
                                    Log.d(
                                        "ERROR-DatosUsuario",
                                        "Error al obtener los datos de usuario"
                                    )
                                }

                            } else Log.d(
                                "ERROR-SIGN",
                                "Error al iniciar sesion, email o contraseña incorrectos"
                            )
                        }.addOnFailureListener {
                            Toast.makeText(context,"¡Credenciales no validas!",Toast.LENGTH_SHORT).show()
                        }
                }
                else{
                    Toast.makeText(context,"¡Los campos no pueden estar vacios!",Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Log.d("ERROR-SIGN-v2", "Error al intentar iniciar sesion")
            }
        }
    }

    /*
       Esta funcion almacena una lista temporal.
       LLama a la BD en firebase, a la coleccion usuarios, pero no pide un documento en especifico, pues se requieren todos.
       Los datos se obtienen en una variable (lista) llamada UsuariosObtenidos, el cual convierte a objetos de tipo Usuario los datos obtenidos.
       Se añaden cada uno de estos usuarios a la lista temporal
       Finalmente, se iguala la lista temporal a la lista de usuarios del viewmodel. Ademas de que esta será aleatoria
     */
    fun obtenerListaUsuarios() {
        viewModelScope.launch {
            var listatemporal = mutableListOf<User>()
            try {
                VMFireDB.collection("Usuarios").get().addOnSuccessListener {
                    var UsuariosObtenidos = it.toObjects<User>()
                    for (usuario in UsuariosObtenidos) {
                        listatemporal.add(usuario)
                    }
                    listaUsuarios = listatemporal.shuffled().toMutableList()
                }
            } catch (e: Exception) {
                e.toString()
            }
        }
    }

    /**
     * Recibe un show por parametros, y si el show existe en la lista de series del usuario, este no podrá añadirlo, ya que ya esta calificado.
     * Si no es asi, es decir, no esta en la lista, se asignará el valor de show a la variable _showinsertar
     * Una vez hecho esto, se ejecutará la funcion lambda 'onadding'
     * @param show es el show que se recibe por parametros y que se comprobará en la lista
     * @param onadding lambda que se ejecutará si la operacion es exitosa.
     */
    @SuppressLint("ShowToast")
    fun comprobacionSerie(show: ShowState, onadding: () -> Unit, context: Context) {
        var disponible = true
        viewModelScope.launch {
            try {
                for (serie in _user.value.listaSeries!!){
                    if (serie.titulo==show.titulo){
                        disponible=false
                    }
                }
                if (disponible==true){
                    _showInsertar.value = show
                    onadding()
                }
                else{
                    Log.d("error-calificado-serie","serie ya calificada")
                    Toast.makeText(context,"Serie ya calificada!",Toast.LENGTH_SHORT).show()

                }
            } catch (e: Exception) {
                Log.d("ERROR-Serie-insert", "Error al insertar la serie")
            }
        }
    }


    /**
     * Se comprueba si los campos reseña y puntuacion no son vacios y si son validos
     * Se crea un show con los datos copiados del show que se quiere calificar.
     * A estos se le añaden los campos reseña y puntuacion introducidos por el usuario
     * El show creado se añade a la lista de series del usuario
     * Una vez añadida, se actualiza la lista de series del usuario en la base de datos
     *  Se llama a la funcion lambda pasada por parametros, a la funcion ResetSeriesInsert y se recuperan de nuevo las series del usuario
     *  @param navegacion se ejecuta una vez se actualiza correctamente la BD
     */
    fun anadirSerieDB(navegacion: () -> Unit) {
        viewModelScope.launch {
            try {
                if (resena != "" && puntuacion.toInt() >= 0 && puntuacion.toInt() <= 10) {
                    //Se establece a la serie que se quiere insertar la puntuacion asignada
                    var show = _showInsertar.value.copy()
                    show.mipuntuacion = puntuacion
                    show.miresena = resena
                    _user.value.listaSeries?.add(show)
                    //Se añade a la lista local la serie de usuarios
                    Log.d(
                        "especificaciones",
                        _showInsertar.value.puntuacion.toString() + showInsertar.value.titulo.toString()
                    )
                    //Se restablecen los valores
                    VMFireDB.collection("Usuarios")
                        .document(VMFireAuth.currentUser?.email.toString())
                        .update("listaSeries", _user.value.listaSeries).addOnSuccessListener {
                        navegacion()
                        OnExitInsert()
                        recuperarSeriesUsuario()
                        Log.d("inserccioncorrecta", "serie insertada")
                    }
                } else {
                    Log.d("ERROR-INSERCCION-SERIE", "La reseña/puntuacion no pueden ser nul@s!")
                }
            } catch (e: Exception) {
                Log.d("ERROR-TRY-INSERCCION-SERIE", "Error al intentar añadir la serie")
            }
        }
    }

    /**
     * @param show show que se pasa por parametros
     * La variable _showEstadisticas recibe el valor del show pasado por parametros
     */
    fun seeFullyShowDetails(show:ShowState){
        viewModelScope.launch {
            try {
                _showEstadisticas.value = show
            }
            catch (e:Exception){
                Log.d("ERROR-ESTADISICAS-SERIE","Excepcion al")
            }
        }
    }

    fun changeSortingtoNormal(){
        _sorted.value= "medio"
    }

    /*
    Cambia el valor de _sorte a 'alto' para que se muestren las series ordenadas de mayor a menor
     */
    fun changeSortingtoHigh(){
        _sorted.value="alto"
    }

    /*
    Cambia el valor de _sorte a 'bajo' para que se muestren las series ordenadas de menor a mayor
     */

    fun changeSortingtoLow(){
        _sorted.value = "bajo"
    }

    /**
     * Funcion que recibe un show, comprueba si los digitos de la puntuacion superan el permitido y si es asi, acorta los digitos de la puntuacion a 1 digito
     * Si no, devuelve la puntuacion tal como es.
     * @param show Serie que se recibe por parametros y de la que se comprueba su puntuacion
     * @return String que contiene la puntuacion solo con el primer digito o la puntuacion normal
     */

    fun checkShowRate(show: ShowState): String {
        var puntuacion = ""
        if (show.puntuacion.toString().length >= 4) {
            puntuacion = show.puntuacion!!.toString().substring(0, 3)
        } else {
            puntuacion = show.puntuacion.toString()
        }
        return puntuacion
    }

    /*
        Funcion que llama a ResetSerieValue para restablecer los campos
        Restablece el valor de showinsertar a serie nula
     */

    fun OnExitInsert() {
        ResetSerieValues()
        _showInsertar.value = ShowState()
    }

    /*
    Funcion que restablece los valores de inserccion de serie
     */
    fun ResetSerieValues() {
        resena = ""
        puntuacion = ""
    }


    /**
     * Funcion que contiene una lista temporal
     * Llama a la BD, y recibe en una lista de series del usuario actual
     * Añade cada serie a la lista temporal y finalmente iguala esta a la lista de series del usuario
     *
     */
    fun recuperarSeriesUsuario() {
        viewModelScope.launch {
            try {
                var listatemporal = mutableListOf<ShowState>()
                if (emaiLRegisterLogin.isNotEmpty()) {
                    var documento =
                        VMFireDB.collection("Usuarios").document(emaiLRegisterLogin).get().await()
                    var lista = documento as MutableList<ShowState>
                    for (show in lista) {
                        listatemporal.add(show)
                    }
                    _user.value.listaSeries = listatemporal
                }
            } catch (e: Exception) {
                Log.d("ERROR-RECUPERAR", "Error al recuperar las series")
            }
        }
    }

    /**
     * Funcion que recibe un show
     * Se comprueba si la lista de series contiene el show.
     * Si lo contiene, lo elimina, y posteriormente, actualiza la lista de series del usuario en la base de datos
     * @param show es la serie que se desea eliminar
     */

    fun deletePelicula(show: ShowState) {
        viewModelScope.launch {
            try {
                if (_user.value.listaSeries!!.contains(show)) {
                    _user.value.listaSeries!!.remove(show)
                    VMFireDB.collection("Usuarios")
                        .document(VMFireAuth.currentUser?.email.toString())
                        .update("listaSeries", _user.value.listaSeries)
                } else {
                    Log.d("ERROR-Eliminacion", "Error al eliminar show")
                }
            } catch (e: Exception) {
                Log.d("EXCEPCION-DELETE", "Excepcion durante eliminacion de show")
            }
        }
    }

    /**
     * Vacia la lista de series del usuario
     * A continuacion, actualiza la lista de usuario en la base de datos
     */
    fun deleteAllShows() {
        viewModelScope.launch {
            try {
                _user.value.listaSeries?.clear()
                VMFireDB.collection("Usuarios").document(VMFireAuth.currentUser?.email.toString())
                    .update("listaSeries", _user.value.listaSeries)
            } catch (e: Exception) {
                Log.d("ERROR-ELIMINAR-COMPLETO", "Excepcion al intentar eliminar todas las seriess")
            }
        }
    }

    /*
    Cambia el estado de desc al contrario.
    Dependiendo del estado, se mostrará la descripcion o la reseña
     */
    fun changeDescState(){
        _desc.value = !desc.value
    }

    /**
     * Establece el valor de desc a true para que cuando se navege a la pantalla, siempre aparezca primero la descripcion
     * @param navegacion se ejecuta al llamar a la funcion
     */
    fun onExitMyFullShowScreen(navegacion: () -> Unit){
       viewModelScope.launch {
           try {
               navegacion()
               _showEstadisticas.value = ShowState()
               _desc.value = true
           }
           catch (e:Exception){
               Log.d("ERROR-SALIR-SHOWSCREEN","Error al salir de la pantalla de estadisticas de show")
           }
       }
    }

    /**
     * Llama a la funcion lambda y establece en usuario nulo el usuarioa actual.
     * Sin embargo, no restablece los campos para que el usuario pueda volver a iniciar sesion desde la pantalla login sin necesidad de escribir sus credenciales.
     * @param navegacion se ejecuta al llamar a la funcion
     */
    fun cerrarSesion(navegacion: () -> Unit) {
        try {
            navegacion()
            _user.value = User()
        } catch (e: Exception) {
            Log.d("ERROR-CERRAR", "Error al intentar cerrar sesion")
        }
    }

    /*
    Borra los campos de registro/login
     */
    fun borrarCamposRegistroLogin() {
        imagenRegister = ""
        nombreRegister = ""
        emaiLRegisterLogin = ""
        passwordRegisterLogin = ""
    }

    /**
     * Funcion que recibe un show
     * Si el titulo de un show es mas largo de lo posible, se acorta y se añaden puntos suspensivos
     * @param show es el show del que se comprobará la longitud de su titulo
     * @return Titulo del show
     */
    fun setShortTitle(show: ShowState): String {
        var title = ""
        if (show.titulo!!.length >= 25) {
            title = show.titulo!!.substring(0, 25) + "..."
        } else
            title = show.titulo!!
        return title
    }

    /*
    Obtiene los nombres de todos los usuarios de la base de datos
     */
    fun obtenerNombres(){
        viewModelScope.launch {
            try {
                var listatemporal = mutableListOf<String>()
                VMFireDB.collection("Usuarios").get().addOnSuccessListener {
                    for (user in it){
                        listatemporal.add(user.get("nombre").toString())
                    }
                    listanombres=listatemporal
                    Log.d("lista",listanombres.toString())
                }
            }
            catch (e:Exception){
                Log.d("exceptioname","nombre no dispo")
            }
        }
    }

}