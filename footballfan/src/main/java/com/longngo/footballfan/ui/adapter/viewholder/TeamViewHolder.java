package com.longngo.footballfan.ui.adapter.viewholder;

import android.view.View;
import android.widget.TextView;

import com.longngo.footballfan.R;
import com.longngo.footballfan.ui.viewmodel.TeamVM;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Long on 10/5/2016.
 */

public class TeamViewHolder extends BaseViewHolder<TeamVM> {
    @BindView(R.id.des)
    TextView des;
    public TeamViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public  void bind(TeamVM item) {
        des.setText(item.toString());
    }

//    private class HttpImageRequestTask extends AsyncTask<Void, Void, Drawable> {
//        @Override
//        protected Drawable doInBackground(Void... params) {
//            try {
//
//
//                final URL url = new URL("http://upload.wikimedia.org/wikipedia/commons/e/e8/Svg_example3.svg");
//                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//                InputStream inputStream = urlConnection.getInputStream();
//                SVG svg = SVGParser. getSVGFromInputStream(inputStream);
//                Drawable drawable = svg.createPictureDrawable();
//                return drawable;
//            } catch (Exception e) {
//                Log.e("ContactActivity", e.getMessage(), e);
//            }
//
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Drawable drawable) {
//            // Update the view
//            updateImageView(drawable);
//        }
//    }





}
