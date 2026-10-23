package com.stremio.tv.views.menu;

import android.content.ComponentCallbacks;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.platform.ComposeView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.ViewModelStoreOwnerDefaults;
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner;
import androidx.lifecycle.viewmodel.compose.ViewModelKt;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.navigation.NavController;
import androidx.navigation.fragment.FragmentKt;
import cafe.adriel.lyricist.Lyricist;
import com.stremio.common.platform.FetcherFactoryProviderKt;
import com.stremio.common.translations.StringsProviderKt;
import com.stremio.common.viewmodels.SettingsViewModel;
import com.stremio.common.viewmodels.UserProfilesState;
import com.stremio.common.viewmodels.UserProfilesViewModel;
import com.stremio.core.models.UserProfile;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import org.koin.android.ext.android.AndroidKoinScopeExtKt;
import org.koin.core.qualifier.Qualifier;

/* JADX INFO: compiled from: NavigationMenuFragment.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J$\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016R\u001b\u0010\u0004\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007Ê\u0001\f\b\u0017\u0012\b\b\u0018\u0012\u0004\b\u0003\u0010\u0000¨\u0006\u0012²\u0006\n\u0010\u0013\u001a\u00020\u0014X\u008a\u0084\u0002²\u0006\n\u0010\u0015\u001a\u00020\u0016X\u008a\u0084\u0002"}, d2 = {"Lcom/stremio/tv/views/menu/NavigationMenuFragment;", "Landroidx/fragment/app/Fragment;", "<init>", "()V", "userProfilesViewModel", "Lcom/stremio/common/viewmodels/UserProfilesViewModel;", "getUserProfilesViewModel", "()Lcom/stremio/common/viewmodels/UserProfilesViewModel;", "userProfilesViewModel$delegate", "Lkotlin/Lazy;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", TtmlNode.RUBY_CONTAINER, "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "Stremio:androidTV-com.stremio.one-1.11.2-40000002_release", "userProfiles", "Lcom/stremio/common/viewmodels/UserProfilesState;", "hasPremium", "", "Landroidx/compose/runtime/internal/StabilityInferred;", "parameters"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class NavigationMenuFragment extends Fragment {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: userProfilesViewModel$delegate, reason: from kotlin metadata */
    private final Lazy userProfilesViewModel;

    /* JADX WARN: Multi-variable type inference failed */
    public NavigationMenuFragment() {
        final NavigationMenuFragment navigationMenuFragment = this;
        LazyThreadSafetyMode lazyThreadSafetyMode = LazyThreadSafetyMode.SYNCHRONIZED;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.userProfilesViewModel = LazyKt.lazy(lazyThreadSafetyMode, (Function0) new Function0<UserProfilesViewModel>() { // from class: com.stremio.tv.views.menu.NavigationMenuFragment$special$$inlined$inject$default$1
            /* JADX WARN: Type inference failed for: r3v2, types: [com.stremio.common.viewmodels.UserProfilesViewModel, java.lang.Object] */
            @Override // kotlin.jvm.functions.Function0
            public final UserProfilesViewModel invoke() {
                ComponentCallbacks componentCallbacks = navigationMenuFragment;
                return AndroidKoinScopeExtKt.getKoinScope(componentCallbacks).get(Reflection.getOrCreateKotlinClass(UserProfilesViewModel.class), qualifier, objArr);
            }
        });
    }

    private final UserProfilesViewModel getUserProfilesViewModel() {
        return (UserProfilesViewModel) this.userProfilesViewModel.getValue();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        Context contextRequireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext(...)");
        ComposeView composeView = new ComposeView(contextRequireContext, null, 0, 6, null);
        composeView.setContent(ComposableLambdaKt.composableLambdaInstance(-597708129, true, new Function2() { // from class: com.stremio.tv.views.menu.NavigationMenuFragment$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return NavigationMenuFragment.onCreateView$lambda$0$0(this.f$0, (Composer) obj, ((Integer) obj2).intValue());
            }
        }));
        return composeView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onCreateView$lambda$0$0(NavigationMenuFragment navigationMenuFragment, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C30@1162L11,31@1238L16,32@1318L16,36@1458L332,36@1442L348:NavigationMenuFragment.kt#wivmcv");
        if (!composer.shouldExecute((i & 3) != 2, i & 1)) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-597708129, i, -1, "com.stremio.tv.views.menu.NavigationMenuFragment.onCreateView.<anonymous>.<anonymous> (NavigationMenuFragment.kt:29)");
            }
            final NavController navControllerFindNavController = FragmentKt.findNavController(navigationMenuFragment);
            ComposerKt.sourceInformationMarkerStart(composer, 1729797275, "CC(viewModel)N(viewModelStoreOwner,key,factory,extras)58@2688L7,64@2951L63:ViewModel.kt#3tja67");
            ViewModelStoreOwner current = LocalViewModelStoreOwner.INSTANCE.getCurrent(composer, 6);
            if (current != null) {
                ViewModel viewModel = ViewModelKt.viewModel((KClass<ViewModel>) Reflection.getOrCreateKotlinClass(SettingsViewModel.class), current, (String) null, (ViewModelProvider.Factory) null, ViewModelStoreOwnerDefaults.getViewModelCreationExtras(current), composer, 0, 0);
                ComposerKt.sourceInformationMarkerEnd(composer);
                final State stateCollectAsState = SnapshotStateKt.collectAsState(navigationMenuFragment.getUserProfilesViewModel().getState(), null, composer, 0, 1);
                final State stateCollectAsState2 = SnapshotStateKt.collectAsState(((SettingsViewModel) viewModel).getHasPremium(), null, composer, 0, 1);
                final UserProfile activeProfile = onCreateView$lambda$0$0$1(stateCollectAsState2) ? onCreateView$lambda$0$0$0(stateCollectAsState).getActiveProfile() : null;
                StringsProviderKt.StringsProvider(ComposableLambdaKt.rememberComposableLambda(-1954774548, true, new Function3() { // from class: com.stremio.tv.views.menu.NavigationMenuFragment$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj, Object obj2, Object obj3) {
                        return NavigationMenuFragment.onCreateView$lambda$0$0$2(navControllerFindNavController, activeProfile, stateCollectAsState, stateCollectAsState2, (Lyricist) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                }, composer, 54), composer, 6);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                throw new IllegalStateException("No ViewModelStoreOwner was provided via LocalViewModelStoreOwner".toString());
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onCreateView$lambda$0$0$2(final NavController navController, final UserProfile userProfile, final State state, final State state2, Lyricist it, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(it, "it");
        ComposerKt.sourceInformation(composer, "CN(it)37@1503L269,37@1480L292:NavigationMenuFragment.kt#wivmcv");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1954774548, i, -1, "com.stremio.tv.views.menu.NavigationMenuFragment.onCreateView.<anonymous>.<anonymous>.<anonymous> (NavigationMenuFragment.kt:37)");
        }
        FetcherFactoryProviderKt.FetcherFactoryProvider(ComposableLambdaKt.rememberComposableLambda(1350752429, true, new Function2() { // from class: com.stremio.tv.views.menu.NavigationMenuFragment$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return NavigationMenuFragment.onCreateView$lambda$0$0$2$0(navController, userProfile, state, state2, (Composer) obj, ((Integer) obj2).intValue());
            }
        }, composer, 54), composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onCreateView$lambda$0$0$2$0(NavController navController, UserProfile userProfile, State state, State state2, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C38@1529L221:NavigationMenuFragment.kt#wivmcv");
        if (!composer.shouldExecute((i & 3) != 2, i & 1)) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1350752429, i, -1, "com.stremio.tv.views.menu.NavigationMenuFragment.onCreateView.<anonymous>.<anonymous>.<anonymous>.<anonymous> (NavigationMenuFragment.kt:38)");
            }
            NavigationMenuKt.NavigationMenu(navController, userProfile, onCreateView$lambda$0$0$0(state).getHasProfiles(), onCreateView$lambda$0$0$1(state2), composer, 0, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    private static final UserProfilesState onCreateView$lambda$0$0$0(State<UserProfilesState> state) {
        return state.getValue();
    }

    private static final boolean onCreateView$lambda$0$0$1(State<Boolean> state) {
        return state.getValue().booleanValue();
    }
}