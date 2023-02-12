package com.example.foodplannerapp.ui.detailsFragment.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.foodplannerapp.R;
import com.example.foodplannerapp.databinding.FragmentDetailsBinding;
import com.example.foodplannerapp.models.ModelMeal;
import com.example.foodplannerapp.models.MySharedPref;
import com.example.foodplannerapp.repo.mealRepo.MealRepo;
import com.example.foodplannerapp.ui.detailsFragment.view.DetailsFragmentDirections;
import com.example.foodplannerapp.ui.detailsFragment.view.DetailsFragmentArgs;
import com.example.foodplannerapp.ui.detailsFragment.presenter.DetailsPresenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;


public class DetailsFragment extends Fragment implements DetailsViewInterface {

    private FragmentDetailsBinding binding;

    private IngredientsAdapter adapter;
    private String mealId;
    private List<String> ingredientList;
    private List<String> measurementList;

    private DetailsPresenter detailsPresenter;

    private boolean flag;

    private ModelMeal modelMeal;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assert getArguments() != null;
        mealId = DetailsFragmentArgs.fromBundle(getArguments()).getMealId();


    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetailsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showHideDetailsProgress();
        adapter = new IngredientsAdapter();
        measurementList = new ArrayList<>();
        ingredientList = new ArrayList<>();
        modelMeal = new ModelMeal();
        flag = false;
        binding.btnPlay.setVisibility(View.GONE);
        detailsPresenter = new DetailsPresenter(this);
        detailsPresenter.getData(mealId, getViewLifecycleOwner());
        RedrawHeart();
        onClicks();


    }

    private void RedrawHeart() {
        detailsPresenter.isFav(mealId).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ModelMeal>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull ModelMeal modelMeal) {
                        if (modelMeal != null) {
                            flag = true;
                            binding.imageFavourite.setImageResource(R.drawable.baseline_favorite_24);
                            binding.imageFavourite.setColorFilter(R.color.yellow);
                        }
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                        Log.e("TAG", "onError: " + e.getMessage());
                    }
                });

    }


    private void setData2(ModelMeal modelMeal) {
        binding.textViewMealName.setText(modelMeal.getStrCategory().concat("\t From \t").concat(modelMeal.getStrArea()));
        binding.textViewSteps.setText(modelMeal.getStrInstructions());
        Glide.with(requireContext())
                .load(modelMeal.getStrMealThumb())
                .into(binding.imageFood);


        setData(modelMeal);

        adapter.setIngredientList((ArrayList<String>) ingredientList);
        adapter.setMeasurementList((ArrayList<String>) measurementList);
        binding.recyclerIngredients.setAdapter(adapter);


        playVideo(modelMeal);
    }


    private void playVideo(ModelMeal mealModel) {
        if (mealModel.getStrYoutube().isEmpty()) {
            binding.video.setVisibility(View.GONE);
            binding.videoView.setVisibility(View.GONE);
            binding.lineAfterVideo.setVisibility(View.GONE);
        } else {
            getViewLifecycleOwner().getLifecycle().addObserver((LifecycleObserver) binding.videoView);
            String[] split = mealModel.getStrYoutube().split("=");

            binding.videoView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onError(@NonNull YouTubePlayer youTubePlayer, @NonNull PlayerConstants.PlayerError error) {
                    super.onError(youTubePlayer, error);

                    Toast.makeText(requireContext(), "Error : " + error, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                    super.onReady(youTubePlayer);

                    try {
                        String videoId = split[1];
                        youTubePlayer.loadVideo(videoId, 0);

                    } catch (Exception e) {
                        Log.e("TAG", "onReady: " + e.getMessage());
                    }
                }
            });

        }


    }

    private void showHideDetailsProgress(){
        binding.progressDetails.setVisibility(View.VISIBLE);
        binding.fakeRoot.setVisibility(View.VISIBLE);
        new Handler().postDelayed(() -> {
            binding.progressDetails.setVisibility(View.GONE);
            binding.fakeRoot.setVisibility(View.GONE);
        }, 2500);
    }

    private void setData(ModelMeal modelMeal) {

        getIngredientList(modelMeal.getStrIngredient1());
        getIngredientList(modelMeal.getStrIngredient2());
        getIngredientList(modelMeal.getStrIngredient3());
        getIngredientList(modelMeal.getStrIngredient4());
        getIngredientList(modelMeal.getStrIngredient5());
        getIngredientList(modelMeal.getStrIngredient6());
        getIngredientList(modelMeal.getStrIngredient7());
        getIngredientList(modelMeal.getStrIngredient8());
        getIngredientList(modelMeal.getStrIngredient9());
        getIngredientList(modelMeal.getStrIngredient10());
        getIngredientList(modelMeal.getStrIngredient11());
        getIngredientList(modelMeal.getStrIngredient12());
        getIngredientList(modelMeal.getStrIngredient13());
        getIngredientList(modelMeal.getStrIngredient15());
        getIngredientList(modelMeal.getStrIngredient16());
        getIngredientList(modelMeal.getStrIngredient17());
        getIngredientList(modelMeal.getStrIngredient18());
        getIngredientList(modelMeal.getStrIngredient19());
        getIngredientList(modelMeal.getStrIngredient20());
        getMeasurementsList(modelMeal.getStrMeasure1());
        getMeasurementsList(modelMeal.getStrMeasure2());
        getMeasurementsList(modelMeal.getStrMeasure3());
        getMeasurementsList(modelMeal.getStrMeasure4());
        getMeasurementsList(modelMeal.getStrMeasure5());
        getMeasurementsList(modelMeal.getStrMeasure6());
        getMeasurementsList(modelMeal.getStrMeasure7());
        getMeasurementsList(modelMeal.getStrMeasure8());
        getMeasurementsList(modelMeal.getStrMeasure9());
        getMeasurementsList(modelMeal.getStrMeasure10());
        getMeasurementsList(modelMeal.getStrMeasure11());
        getMeasurementsList(modelMeal.getStrMeasure12());
        getMeasurementsList(modelMeal.getStrMeasure13());
        getMeasurementsList(modelMeal.getStrMeasure14());
        getMeasurementsList(modelMeal.getStrMeasure15());
        getMeasurementsList(modelMeal.getStrMeasure16());
        getMeasurementsList(modelMeal.getStrMeasure17());
        getMeasurementsList(modelMeal.getStrMeasure18());
        getMeasurementsList(modelMeal.getStrMeasure19());
        getMeasurementsList(modelMeal.getStrMeasure20());

    }


    private List<String> getIngredientList(String ingredientName) {
        if (ingredientName != null && !ingredientName.isEmpty())
            ingredientList.add(ingredientName);
        return ingredientList;
    }

    private List<String> getMeasurementsList(String ingredientName) {
        if (ingredientName != null && !ingredientName.isEmpty())
            measurementList.add(ingredientName);
        return measurementList;
    }

    private void onClicks() {
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_detailsFragment_to_homeFragment);
            }
        });

        binding.imageFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!flag) {
                    modelMeal.setUserId(MySharedPref.getUserId());
                    insertMealToFav(modelMeal);
                    RedrawHeart();
                    Toast.makeText(requireContext(), "inserted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(requireContext(), "Already Inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.imageCalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modelMeal.setUserId(MySharedPref.getUserId());
                DetailsFragmentDirections.ActionDetailsFragmentToDayFragment action =
                        DetailsFragmentDirections.actionDetailsFragmentToDayFragment(modelMeal);
                Navigation.findNavController(view)
                        .navigate(action);
            }
        });
    }


    @Override
    public void onSuccess(ModelMeal modelMeal) {
        setData2(modelMeal);
        this.modelMeal = modelMeal;
    }


    @Override
    public void insertMealToFav(ModelMeal modelMeal) {
        detailsPresenter.insertMeal(modelMeal);
        detailsPresenter.insertMealToFireBase(modelMeal);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}