package app.nocamelstyle.cocktailguide.adapters

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.nocamelstyle.cocktailguide.activities.CategoryActivity
import app.nocamelstyle.cocktailguide.activities.CocktailActivity
import app.nocamelstyle.cocktailguide.databinding.ItemCocktailBinding
import app.nocamelstyle.cocktailguide.databinding.ItemSkeletonBinding
import app.nocamelstyle.cocktailguide.models.Drink
import app.nocamelstyle.cocktailguide.utils.startActivity
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.BufferedInputStream
import java.io.IOException
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL


class DrinksAdapter(
    val ctx: CategoryActivity,
    var drinks: List<Drink>,
    val isLoaded: Boolean,
    bitmaps: MutableMap<String?, Bitmap?>? = null
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var images = mutableMapOf<String?, Bitmap?>()

    private fun downloadImage(iUrl: String): Bitmap? {
        var bitmap: Bitmap? = null
        var conn: HttpURLConnection? = null
        var bufStream: BufferedInputStream? = null
        try {
            Log.v("test", "Starting loading image by URL: $iUrl")
            conn = URL(iUrl).openConnection() as HttpURLConnection
            conn.doInput = true
            conn.setRequestProperty("Connection", "Keep-Alive")
            conn.connect()
            bufStream = BufferedInputStream(conn.inputStream, 8192)
            bitmap = BitmapFactory.decodeStream(bufStream)
            bufStream.close()
            conn.disconnect()
            bufStream = null
            conn = null
        } catch (ex: MalformedURLException) {
            Log.e("test", "Url parsing was failed: $iUrl")
        } catch (ex: IOException) {
            Log.d("test", "$iUrl does not exists")
        } catch (e: OutOfMemoryError) {
            Log.w("test", "Out of memory!!!")
            return null
        } finally {
            if (bufStream != null) try {
                bufStream.close()
            } catch (ex: IOException) {
            }
            conn?.disconnect()
        }
        return bitmap
    }

    private fun loadImages() {
        GlobalScope.launch {
            launch(Dispatchers.IO) {
                drinks.forEach {
                    images[it.idDrink] = downloadImage(it.strDrinkThumb ?: "")
                }
                launch(Dispatchers.Main) {
                    ctx.reloadAdapter(drinks, images)
                }
            }
        }
    }

    init {
        if (isLoaded) {
            images = bitmaps!!
        } else
            loadImages()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return if (!isLoaded)
            SkeletonHolder(ItemSkeletonBinding.inflate(layoutInflater, parent, false))
        else
            Holder(ItemCocktailBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is Holder) holder.bind(position)
    }

    override fun getItemCount() = drinks.size

    inner class SkeletonHolder(view: ItemSkeletonBinding): RecyclerView.ViewHolder(view.root)

    inner class Holder(private val view: ItemCocktailBinding): RecyclerView.ViewHolder(view.root) {

        @SuppressLint("SetTextI18n")
        fun bind(position: Int) {
            drinks[position].let {
                view.apply {

                    drinkImg.setImageBitmap(images[it.idDrink])
                    drinkName.text = it.strDrink
                    drinkType.text = "id: ${it.idDrink}"

                    container.setOnClickListener {  _ ->
                        ctx.startActivity<CocktailActivity> {
                            putExtra("drink", Gson().toJson(it))
                        }
                    }
                }
            }
        }

    }

}