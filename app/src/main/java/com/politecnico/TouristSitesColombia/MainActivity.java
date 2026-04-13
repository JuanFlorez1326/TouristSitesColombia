package com.politecnico.TouristSitesColombia;

import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.politecnico.TouristSitesColombia.databinding.ActivityMainBinding;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private NavController navController;

    private boolean isExpanded = false;
    private int collapsedWidth;
    private int expandedWidth;

    private List<LinearLayout> menuItems;
    private List<ImageView> menuIcons;
    private List<TextView> menuTexts;
    private List<Integer> menuDestinations;

    private final String COLOR_ACTIVE_TINT = "#3B82F6";
    private final String COLOR_INACTIVE_TINT = "#94A3B8";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        collapsedWidth = (int) (70 * getResources().getDisplayMetrics().density);
        expandedWidth = (int) (220 * getResources().getDisplayMetrics().density);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_content_main);
        if (navHostFragment != null) {
            navController = navHostFragment.getNavController();
        }

        inicializarMenu();
    }

    private void inicializarMenu() {
        menuItems = Arrays.asList(binding.itemPerfil, binding.itemGaleria, binding.itemRecomendaciones, binding.itemVideos, binding.itemFavoritos);
        menuIcons = Arrays.asList(binding.iconPerfil, binding.iconGaleria, binding.iconRecomendaciones, binding.iconVideos, binding.iconFavoritos);
        menuTexts = Arrays.asList(binding.textPerfil, binding.textGaleria, binding.textRecomendaciones, binding.textVideos, binding.textFavoritos);
        menuDestinations = Arrays.asList(R.id.nav_perfil, R.id.nav_galeria, R.id.nav_recomendaciones, R.id.nav_videos, R.id.nav_favoritos);

        binding.btnMenuToggle.setOnClickListener(v -> toggleSidebar());

        for (int i = 0; i < menuItems.size(); i++) {
            final int index = i;
            menuItems.get(i).setOnClickListener(v -> {
                navController.navigate(menuDestinations.get(index));
                if (isExpanded) toggleSidebar();
            });
        }

        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            actualizarUISeleccion(destination.getId());
        });
    }

    private void toggleSidebar() {
        ValueAnimator anim = ValueAnimator.ofInt(isExpanded ? expandedWidth : collapsedWidth, isExpanded ? collapsedWidth : expandedWidth);

        anim.addUpdateListener(valueAnimator -> {
            ViewGroup.LayoutParams lp = binding.sidebarContainer.getLayoutParams();
            lp.width = (Integer) valueAnimator.getAnimatedValue();
            binding.sidebarContainer.setLayoutParams(lp);
        });
        anim.setDuration(250);
        anim.start();

        isExpanded = !isExpanded;

        float targetAlpha = isExpanded ? 1f : 0f;
        for (TextView tv : menuTexts) {
            tv.animate().alpha(targetAlpha).setDuration(200).start();
        }
    }

    private void actualizarUISeleccion(int destinationId) {
        for (int i = 0; i < menuItems.size(); i++) {
            boolean isActive = (destinationId == menuDestinations.get(i));

            if (isActive) {
                menuItems.get(i).setBackgroundResource(R.drawable.bg_sidebar_active);
            } else {
                menuItems.get(i).setBackgroundColor(Color.TRANSPARENT);
            }

            int tintColor = Color.parseColor(isActive ? COLOR_ACTIVE_TINT : COLOR_INACTIVE_TINT);
            menuIcons.get(i).setColorFilter(tintColor);
            menuTexts.get(i).setTextColor(tintColor);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp() || super.onSupportNavigateUp();
    }
}