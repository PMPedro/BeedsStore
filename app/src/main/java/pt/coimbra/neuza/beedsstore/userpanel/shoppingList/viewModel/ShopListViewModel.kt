package pt.coimbra.neuza.beedsstore.userpanel.shoppingList.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pt.coimbra.neuza.beedsstore.adminpanel.model.Beed

class ShopListViewModel : ViewModel() {
    private val _beedsListShop = MutableLiveData<List<Beed>>(emptyList())
     val beedsListShop : LiveData<List<Beed>> = _beedsListShop


    fun addBeedShop(beed : Beed){
        val current = _beedsListShop.value ?: emptyList()
        _beedsListShop.value = current + beed
    }

    fun removeBeed(beed : Beed){
        val current = _beedsListShop.value ?: emptyList()
        _beedsListShop.value = current - beed
    }

    fun clearList() {
        _beedsListShop.value = emptyList()
    }

    //for a real project , create a beedsShop (data class) with beed info, adress, quantites of each item and etc. Would add Payment, and Shop Repository , to manager shopping list with firebase
}