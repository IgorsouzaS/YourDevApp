package com.yourdevfilmes.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.yourdevfilmes.R
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        var imageView : ImageView = findViewById(R.id.img_detail);

        supportPostponeEnterTransition();

        Picasso.with(this)
            .load(intent.getStringExtra("url"))
            .fit()
            .noFade()
            .centerCrop()
            .into(imageView, object: Callback {
                override fun onSuccess() {
                    supportStartPostponedEnterTransition();
                    tvSummaryDetail.text = intent.getStringExtra("summary")
                }

                override fun onError() {
                    //TODO mostrar erro
                }
            });

    }
}
