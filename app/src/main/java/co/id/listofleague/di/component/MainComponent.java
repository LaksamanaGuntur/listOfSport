package co.id.listofleague.di.component;
import co.id.listofleague.di.module.MainActivityModule;
import co.id.listofleague.di.scope.ActivityScope;
import co.id.listofleague.ui.detail.DetailActivity;
import co.id.listofleague.ui.main.MainActivity;
import dagger.Subcomponent;

/**
 * Created by Laksamana Guntur Dzulfikar.
 * Android Developer
 */

@ActivityScope
@Subcomponent(
        modules = MainActivityModule.class
)
public interface MainComponent {
    MainActivity inject(MainActivity mainActivity);
    DetailActivity inject(DetailActivity detailActivity);
}
