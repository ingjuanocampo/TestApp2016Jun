package com.juanocampo.test.androidtest.fragments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.TextView;

import com.google.common.base.Preconditions;
import com.juanocampo.test.androidtest.R;
import com.juanocampo.test.androidtest.adapter.CardsAdapter;
import com.juanocampo.test.androidtest.api.ProxyApp;
import com.juanocampo.test.androidtest.api.ResponsesCalls;
import com.juanocampo.test.androidtest.model.Entry;
import com.juanocampo.test.androidtest.model.ServiceResponse;
import com.juanocampo.test.androidtest.util.AnimationsCommons;

/**
 * Created by juanocampo on 6/19/16.
 */
public class ListStoresFragment extends Fragment implements ResponsesCalls, CardsAdapter.CardAdapterActions {

    private CardsAdapter adapter;

    public interface ListStoresActions {
        void onCardClicked(Entry entry);
    }

    private RecyclerView recyclerView;
    private TextView titleTooblar;
    private ListStoresActions listStoresActions;

    public static Fragment newInstance() {
        Bundle args = new Bundle();
        ListStoresFragment fragment = new ListStoresFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Preconditions.checkState(getActivity() instanceof  ListStoresActions);

        listStoresActions = (ListStoresActions) getActivity();

        titleTooblar = (TextView) getView().findViewById(R.id.title_toolbar);
        titleTooblar.setText(getString(R.string.app_name));

        recyclerView = (RecyclerView) getView().findViewById(R.id.recycler_view);

        if (AnimationsCommons.isTablet(getContext())) {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(gridLayoutManager);

        } else {
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        }

        ProxyApp proxyApp = new ProxyApp(getContext());

        proxyApp.getItunesIntance().getStoresSync(this);

    }

    @Override
    public void onAllStoresArrives(ServiceResponse serviceResponse) {
        adapter = new CardsAdapter(getContext(), serviceResponse.getFeed().getEntries(), this);
        recyclerView.setAdapter(adapter);
        adapter.updateItems(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        makeScreenAnimation();

    }

    private void makeScreenAnimation() {
        if (adapter != null) {
            adapter.updateItems(true);
        }
        if (titleTooblar != null) {
            titleTooblar.setScaleY(0.1f);
            titleTooblar.setPivotY(0);

            titleTooblar.animate()
                    .scaleY(1)
                    .setDuration(200)
                    .setInterpolator(new AccelerateInterpolator())
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                        }
                    })
                    .start();
        }
    }

    @Override
    public void onError(String errorMsn) {

    }

    @Override
    public void onCardClicked(Entry entry) {
        listStoresActions.onCardClicked(entry);

    }
}
