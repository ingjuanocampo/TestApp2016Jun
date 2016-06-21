package com.juanocampo.test.androidtest.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.common.base.Preconditions;
import com.juanocampo.test.androidtest.R;
import com.juanocampo.test.androidtest.model.Entry;
import com.juanocampo.test.androidtest.util.AnimationsCommons;
import com.squareup.picasso.Picasso;

/**
 * Created by juanocampo on 6/19/16.
 */
public class DetailFragment extends Fragment {

    private static final String ENTRY_ITEM = "Entry_item";

    public static DetailFragment newInstance(@NonNull Entry entry) {

        Bundle args = new Bundle();
        args.putSerializable(ENTRY_ITEM, entry);
        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.detail_fragment_view, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Preconditions.checkNotNull(getArguments());
        Preconditions.checkState(getArguments().containsKey(ENTRY_ITEM));

        Entry entry = (Entry) getArguments().getSerializable(ENTRY_ITEM);


        TextView title = (TextView) getView().findViewById(R.id.title_toolbar);
        TextView titleApp = (TextView) getView().findViewById(R.id.detail_title);
        TextView price = (TextView)  getView().findViewById(R.id.detail_price);
        TextView category = (TextView) getView().findViewById(R.id.detail_category);
        TextView description = (TextView) getView().findViewById(R.id.detail_description);
        TextView company = (TextView) getView().findViewById(R.id.detail_company);
        TextView releaseDate = (TextView) getView().findViewById(R.id.detail_release_date);
        TextView copyRights = (TextView) getView().findViewById(R.id.detail_copy_rights);
        ImageView imageView = (ImageView) getView().findViewById(R.id.detail_image);

        title.setText(entry.getName().getLabel());
        titleApp.setText(entry.getTitle().getLabel());
        price.setText(entry.getPrice().getPriceAttributes().getCurrency() + " " + entry.getPrice().getPriceAttributes().getAmount());
        category.setText(entry.getCategory().getAttributes().getLabel());
        company.setText(entry.getLink().getAttributes().getHref());
        description.setText(entry.getSummary().getLabel());
        releaseDate.setText(entry.getReleaseDate().getAttributes().getLabel());
        copyRights.setText(entry.getRights().getLabel());


        Picasso.with(getContext()).
                load(entry.getImagesList().get(2).getLabel()).
                placeholder(R.mipmap.ic_launcher).transform(AnimationsCommons.getCircleTransformation())
                .into(imageView);




    }
}
