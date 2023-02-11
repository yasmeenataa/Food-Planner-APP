package com.example.foodplannerapp.ui.detailsFragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.foodplannerapp.R;
import com.example.foodplannerapp.databinding.FragmentDetailsBinding;
import com.example.foodplannerapp.models.ModelMeal;
import com.example.foodplannerapp.repo.mealRepo.MealRepo;
import com.example.foodplannerapp.ui.allCategory.view.AllCategoriesFragmentArgs;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;

import java.util.ArrayList;
import java.util.List;


public class DetailsFragment extends Fragment {

    private FragmentDetailsBinding binding;

    private boolean flag;
    private IngredientsAdapter adapter;
    private String mealId;
    private List<String> ingredientList;
    private List<String> measurementList;


    private ModelMeal modelMeal;

    private MealRepo repo;


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
        adapter = new IngredientsAdapter();
        onClicks();
        measurementList = new ArrayList<>();
        ingredientList = new ArrayList<>();
        modelMeal = new ModelMeal();
        repo = MealRepo.getMealRepoInstance();
        getData();
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    private void getData() {

        repo.getMealById(mealId);
        repo.getListLiveData().observe(getActivity(), new Observer<ArrayList<ModelMeal>>() {
            @Override
            public void onChanged(ArrayList<ModelMeal> modelMeals) {

                modelMeal = modelMeals.get(0);

                binding.textViewMealName.setText(modelMeal.getStrCategory().concat("\t From \t").concat(modelMeal.getStrArea()));
                binding.textViewSteps.setText(modelMeal.getStrInstructions());
                Glide.with(requireContext())
                        .load(modelMeal.getStrMealThumb())
                        .into(binding.imageFood);


                setData(modelMeal);

                adapter.setIngredientList((ArrayList<String>) ingredientList);
                adapter.setMeasurementList((ArrayList<String>) measurementList);
                binding.recyclerIngredients.setAdapter(adapter);

//                Log.e("TAG", "onChanged: before click flag "+flag+"");
//
//                if (isFlag()) {
                    Log.e("TAG", "onChanged: after clicke flag "+flag+"");
                    playVideo(modelMeal);
//                }


            }
        });

    }

    public boolean isFlag() {
        return flag;
    }

    private void playVideo(ModelMeal mealModel) {

        if (mealModel.getStrYoutube().isEmpty()) {
            binding.video.setVisibility(View.GONE);
            binding.videoView.setVisibility(View.GONE);
            binding.lineAfterVideo.setVisibility(View.GONE);
        } else {
            getLifecycle().addObserver((LifecycleObserver) binding.videoView);
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

        binding.btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFlag(true);
                Log.e("TAG", "onClick: inside click "+flag+"");
            }
        });


    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


}