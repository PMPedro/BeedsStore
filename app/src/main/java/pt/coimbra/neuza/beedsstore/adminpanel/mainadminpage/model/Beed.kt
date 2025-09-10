package pt.coimbra.neuza.beedsstore.adminpanel.mainadminpage.model

data class Beed(
    val description : String = "",
    val title : String = "" ,
    val price : Double= 0.0,
    val imageURL: String = ""
)

var beedList =  mutableListOf<Beed>()
