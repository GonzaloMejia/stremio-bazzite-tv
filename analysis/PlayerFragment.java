package com.stremio.tv.views.player;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.platform.ComposeView;
import androidx.fragment.app.Fragment;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.navigation.NavArgsLazy;
import androidx.navigation.NavController;
import androidx.navigation.fragment.FragmentKt;
import cafe.adriel.lyricist.Lyricist;
import com.stremio.common.platform.FetcherFactoryProviderKt;
import com.stremio.common.translations.StringsProviderKt;
import com.stremio.tv.util.LoggingUtil;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: PlayerFragment.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J$\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u0013H\u0016R\u001b\u0010\u0004\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007Ê\u0001\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\u0003\u0010\u0000¨\u0006\u0014"}, d2 = {"Lcom/stremio/tv/views/player/PlayerFragment;", "Landroidx/fragment/app/Fragment;", "<init>", "()V", "args", "Lcom/stremio/tv/views/player/PlayerFragmentArgs;", "getArgs", "()Lcom/stremio/tv/views/player/PlayerFragmentArgs;", "args$delegate", "Landroidx/navigation/NavArgsLazy;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", TtmlNode.RUBY_CONTAINER, "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "", "Stremio:androidTV-com.stremio.one-1.11.2-40000002_release", "Landroidx/compose/runtime/internal/StabilityInferred;", "parameters"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class PlayerFragment extends Fragment {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: args$delegate, reason: from kotlin metadata */
    private final NavArgsLazy args;

    public PlayerFragment() {
        final PlayerFragment playerFragment = this;
        this.args = new NavArgsLazy(Reflection.getOrCreateKotlinClass(PlayerFragmentArgs.class), new Function0<Bundle>() { // from class: com.stremio.tv.views.player.PlayerFragment$special$$inlined$navArgs$1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Bundle invoke() {
                Bundle arguments = playerFragment.getArguments();
                if (arguments != null) {
                    return arguments;
                }
                throw new IllegalStateException("Fragment " + playerFragment + " has null arguments");
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final PlayerFragmentArgs getArgs() {
        return (PlayerFragmentArgs) this.args.getValue();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        final NavController navControllerFindNavController = FragmentKt.findNavController(this);
        final Function0 function0 = new Function0() { // from class: com.stremio.tv.views.player.PlayerFragment$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return PlayerFragment.onCreateView$lambda$0(this.f$0, navControllerFindNavController);
            }
        };
        Context contextRequireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext(...)");
        ComposeView composeView = new ComposeView(contextRequireContext, null, 0, 6, null);
        composeView.setContent(ComposableLambdaKt.composableLambdaInstance(-1426532159, true, new Function2() { // from class: com.stremio.tv.views.player.PlayerFragment$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return PlayerFragment.onCreateView$lambda$1$0(navControllerFindNavController, this, function0, (Composer) obj, ((Integer) obj2).intValue());
            }
        }));
        return composeView;
    }

    static final Unit onCreateView$lambda$0(PlayerFragment playerFragment, final NavController navController) {
        View view = playerFragment.getView();
        if (view != null) {
            view.post(new Runnable() { // from class: com.stremio.tv.views.player.PlayerFragment$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    navController.popBackStack();
                }
            });
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onCreateView$lambda$1$0(final NavController navController, final PlayerFragment playerFragment, final Function0 function0, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C32@1016L150,32@1000L166:PlayerFragment.kt#3vay5t");
        if (!composer.shouldExecute((i & 3) != 2, i & 1)) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1426532159, i, -1, "com.stremio.tv.views.player.PlayerFragment.onCreateView.<anonymous>.<anonymous> (PlayerFragment.kt:32)");
            }
            StringsProviderKt.StringsProvider(ComposableLambdaKt.rememberComposableLambda(-91045170, true, new Function3() { // from class: com.stremio.tv.views.player.PlayerFragment$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return PlayerFragment.onCreateView$lambda$1$0$0(navController, playerFragment, function0, (Lyricist) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, composer, 54), composer, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onCreateView$lambda$1$0$0(final NavController navController, final PlayerFragment playerFragment, final Function0 function0, Lyricist it, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(it, "it");
        ComposerKt.sourceInformation(composer, "CN(it)33@1061L87,33@1038L110:PlayerFragment.kt#3vay5t");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-91045170, i, -1, "com.stremio.tv.views.player.PlayerFragment.onCreateView.<anonymous>.<anonymous>.<anonymous> (PlayerFragment.kt:33)");
        }
        FetcherFactoryProviderKt.FetcherFactoryProvider(ComposableLambdaKt.rememberComposableLambda(1759350607, true, new Function2() { // from class: com.stremio.tv.views.player.PlayerFragment$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return PlayerFragment.onCreateView$lambda$1$0$0$0(navController, playerFragment, function0, (Composer) obj, ((Integer) obj2).intValue());
            }
        }, composer, 54), composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onCreateView$lambda$1$0$0$0(NavController navController, PlayerFragment playerFragment, Function0 function0, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C34@1087L39:PlayerFragment.kt#3vay5t");
        if (!composer.shouldExecute((i & 3) != 2, i & 1)) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1759350607, i, -1, "com.stremio.tv.views.player.PlayerFragment.onCreateView.<anonymous>.<anonymous>.<anonymous>.<anonymous> (PlayerFragment.kt:34)");
            }
            PlayerPageKt.PlayerPage(navController, playerFragment.getArgs(), function0, composer, PlayerFragmentArgs.$stable << 3);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        LoggingUtil loggingUtil = LoggingUtil.INSTANCE;
        String simpleName = getClass().getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "getSimpleName(...)");
        loggingUtil.setScreenName(simpleName);
    }
}