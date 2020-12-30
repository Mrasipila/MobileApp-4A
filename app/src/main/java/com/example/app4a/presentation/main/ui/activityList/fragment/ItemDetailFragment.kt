package com.example.app4a.presentation.main.ui.activityList.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.app4a.R
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.fragment_item_detail.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val currency = "desc"

/**
 * A simple [Fragment] subclass.
 * Use the [ItemDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ItemDetailFragment : Fragment() {

    private var curr: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            curr = it.getString(currency)
        }

        println("$curr")
    /*    val g : Gson = GsonBuilder()
            .setLenient()
            .create()
        var t : String = "{"+curr?.substring(9, (curr?.length ?: 0) - 1)+"}"

        val u : String = t.replace(" ", "").replace("{", "{'")
            .replace("=", "'='")
            .replace(",", "','")
            .replace("}", "'}").trim()
        println(u)
    //   "/(['"])?([a-zA-Z0-9_]+)(['"])?:([^\/])/g".toRegex()
        //'"$2":$4')
        val s : FormatterCurr = g.fromJson(u, FormatterCurr::class.java)
        val newtext = getAc*/
        //newtext.text=s.trade_volume_24h_btc

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val inf : View = inflater.inflate(R.layout.fragment_item_detail, container, false)

        val t : String = "{"+curr?.substring( 9, (curr?.length ?: 0) - 1 )+"}"
        val list = t.split(",") as MutableList<String>
        var i = list.size-11
        while(i!=0){
            list.removeAt(5)
            i--
        }

        val tv0 = inf.findViewById<View>(R.id.name) as TextView
        val tv1 = inf.findViewById<View>(R.id.year) as TextView
        val tv2 = inf.findViewById<View>(R.id.country) as TextView
        val tv3 = inf.findViewById<View>(R.id.trustscore_editFragment) as TextView
        val tv4 = inf.findViewById<View>(R.id.trading_incentive_editFragment) as TextView
        val tv5 = inf.findViewById<View>(R.id.trade24_editFragment) as TextView
        val tv6 = inf.findViewById<View>(R.id.trade24n_editFragment) as TextView
        val iv0 = inf.findViewById<View>(R.id.imageView_Fragment) as ImageView

        tv0.text=list.get(1).replace(" name=","")
        tv2.text=list.get(3).replace(" country=","")
        tv1.text=list.get(2).replace( " year_established=","")
        tv3.text=list.get(7).replace( " trust_score=","")
        tv4.text=list.get(6).replace( " has_trading_incentive=","")
        tv5.text=list.get(9).replace( " trade_volume_24h_btc=","")
        tv6.text=list.get(10).replace( " trade_volume_24h_btc_normalized=","")
        Glide.with(iv0.context).load(list.get(5).replace( " image=","")).into(iv0)
        return inf
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @return A new instance of fragment ItemDetailFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String) =
            ItemDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(currency, param1)
                }
            }
    }
}

data class  FormatterCurr(
    var id: String? = "",
    var name: String? = "",
    var year_established: String? = "",
    var country: String? = "",
    var description: String? = "",
    var url: String? = "",
    var image: String? = "",
    var has_trading_incentive: String? = "",
    var trust_score: String? = "",
    var trust_score_rank: String? = "",
    var trade_volume_24h_btc: String? = "",
    var trade_volume_24h_btc_normalized: String? = ""
)