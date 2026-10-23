package com.stremio.common.composables;

import android.content.Context;
import android.content.res.Configuration;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.viewinterop.AndroidView_androidKt;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.ui.PlayerView;
import androidx.media3.ui.SubtitleView;
import com.stremio.common.extensions.PipModeExtKt;
import com.stremio.common.extensions.PlayerExtKt;
import com.stremio.common.players.ExoPlayer;
import com.stremio.common.players.MpvPlayer;
import com.stremio.common.players.Player;
import com.stremio.common.players.VlcPlayer;
import com.stremio.common.players.YouTubePlayer;
import com.stremio.core.types.profile.Profile;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import okhttp3.internal.ws.WebSocketProtocol;
import org.videolan.libvlc.util.VLCVideoLayout;

/* JADX INFO: compiled from: PlayerView.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000L\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a;\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u0007b\u0002\b\tb\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f¢\u0006\u0002\u0010\b\u001a/\u0010\r\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000fH\u0003b\u0002\b\tb\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f¢\u0006\u0002\u0010\u0010\u001a\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0004\u001a\u00020\u0005H\u0002\u001a\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0013\u001a\u00020\u0014H\u0002\u001a\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0013\u001a\u00020\u0014H\u0002\u001a\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002¨\u0006\u001b"}, d2 = {"PlayerView", "", "player", "Lcom/stremio/common/players/Player;", "settings", "Lcom/stremio/core/types/profile/Profile$Settings;", "pipMode", "", "(Lcom/stremio/common/players/Player;Lcom/stremio/core/types/profile/Profile$Settings;ZLandroidx/compose/runtime/Composer;II)V", "Landroidx/compose/runtime/Composable;", "Landroidx/compose/runtime/ComposableTarget;", "applier", "androidx.compose.ui.UiComposable", "View", "view", "Landroid/view/View;", "(Lcom/stremio/common/players/Player;Landroid/view/View;Landroidx/compose/runtime/Composer;I)V", "createExoPlayerView", "Landroidx/media3/ui/PlayerView;", "context", "Landroid/content/Context;", "createVLCPlayerView", "Lorg/videolan/libvlc/util/VLCVideoLayout;", "createMPVPlayerView", "Landroid/view/SurfaceView;", "createYouTubePlayerView", "Landroid/widget/FrameLayout;", "Stremio:common_release"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class PlayerViewKt {
    static final Unit PlayerView$lambda$4(Player player, Profile.Settings settings, boolean z, int i, int i2, Composer composer, int i3) {
        PlayerView(player, settings, z, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final View View$lambda$2$0(View view, Context it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return view;
    }

    static final Unit View$lambda$3(Player player, View view, int i, Composer composer, int i2) {
        View(player, view, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static final void PlayerView(final Player player, final Profile.Settings settings, boolean z, Composer composer, final int i, final int i2) {
        int i3;
        Intrinsics.checkNotNullParameter(settings, "settings");
        Composer composerStartRestartGroup = composer.startRestartGroup(365242071);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(PlayerView)N(player,settings,pipMode)35@1306L7,37@1339L51,38@1415L41,39@1481L41,40@1551L45:PlayerView.kt#2kl541");
        if ((i & 6) == 0) {
            i3 = ((i & 8) == 0 ? composerStartRestartGroup.changed(player) : composerStartRestartGroup.changedInstance(player) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if ((i & 48) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(settings) ? 32 : 16;
        }
        int i4 = i2 & 4;
        if (i4 != 0) {
            i3 |= RendererCapabilities.DECODER_SUPPORT_MASK;
        } else if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i3 |= composerStartRestartGroup.changed(z) ? 256 : 128;
        }
        if (!composerStartRestartGroup.shouldExecute((i3 & 147) != 146, i3 & 1)) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (i4 != 0) {
                z = false;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(365242071, i3, -1, "com.stremio.common.composables.PlayerView (PlayerView.kt:34)");
            }
            ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = composerStartRestartGroup.consume(localContext);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Context context = (Context) objConsume;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1189682646, "CC(remember):PlayerView.kt#9igjgp");
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = createExoPlayerView(context, settings);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            PlayerView playerView = (PlayerView) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1189680224, "CC(remember):PlayerView.kt#9igjgp");
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = createVLCPlayerView(context);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            VLCVideoLayout vLCVideoLayout = (VLCVideoLayout) objRememberedValue2;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1189678112, "CC(remember):PlayerView.kt#9igjgp");
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue3 = createMPVPlayerView(context);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            SurfaceView surfaceView = (SurfaceView) objRememberedValue3;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1189675868, "CC(remember):PlayerView.kt#9igjgp");
            Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue4 = createYouTubePlayerView(context);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            }
            FrameLayout frameLayout = (FrameLayout) objRememberedValue4;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            if (z) {
                composerStartRestartGroup.startReplaceGroup(1774817308);
                ComposerKt.sourceInformation(composerStartRestartGroup, "43@1625L31");
                PipModeExtKt.configurePipMode(playerView, composerStartRestartGroup, 0);
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(1774862475);
                composerStartRestartGroup.endReplaceGroup();
            }
            if (player instanceof ExoPlayer) {
                composerStartRestartGroup.startReplaceGroup(1774894560);
                ComposerKt.sourceInformation(composerStartRestartGroup, "47@1703L27");
                View(player, playerView, composerStartRestartGroup, ExoPlayer.$stable | (i3 & 14));
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(1774935883);
                composerStartRestartGroup.endReplaceGroup();
            }
            if (player instanceof VlcPlayer) {
                composerStartRestartGroup.startReplaceGroup(1774967968);
                ComposerKt.sourceInformation(composerStartRestartGroup, "51@1777L27");
                View(player, vLCVideoLayout, composerStartRestartGroup, VlcPlayer.$stable | (i3 & 14));
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(1775009291);
                composerStartRestartGroup.endReplaceGroup();
            }
            if (player instanceof MpvPlayer) {
                composerStartRestartGroup.startReplaceGroup(1775041376);
                ComposerKt.sourceInformation(composerStartRestartGroup, "55@1851L27");
                View(player, surfaceView, composerStartRestartGroup, MpvPlayer.$stable | (i3 & 14));
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(1775082699);
                composerStartRestartGroup.endReplaceGroup();
            }
            if (player instanceof YouTubePlayer) {
                composerStartRestartGroup.startReplaceGroup(1775118876);
                ComposerKt.sourceInformation(composerStartRestartGroup, "59@1929L31");
                View(player, frameLayout, composerStartRestartGroup, YouTubePlayer.$stable | (i3 & 14));
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(1775164043);
                composerStartRestartGroup.endReplaceGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        final boolean z2 = z;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.stremio.common.composables.PlayerViewKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return PlayerViewKt.PlayerView$lambda$4(player, settings, z2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final void View(final Player player, final View view, Composer composer, final int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(753364114);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(View)N(player,view)68@2083L7,70@2127L106,70@2096L137,78@2281L39,78@2239L81,86@2450L8,82@2326L139:PlayerView.kt#2kl541");
        if ((i & 6) == 0) {
            i2 = ((i & 8) == 0 ? composerStartRestartGroup.changed(player) : composerStartRestartGroup.changedInstance(player) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(view) ? 32 : 16;
        }
        if (!composerStartRestartGroup.shouldExecute((i2 & 19) != 18, i2 & 1)) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(753364114, i2, -1, "com.stremio.common.composables.View (PlayerView.kt:67)");
            }
            ProvidableCompositionLocal<Configuration> localConfiguration = AndroidCompositionLocals_androidKt.getLocalConfiguration();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = composerStartRestartGroup.consume(localConfiguration);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Configuration configuration = (Configuration) objConsume;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1866554980, "CC(remember):PlayerView.kt#9igjgp");
            int i3 = i2 & 14;
            boolean zChangedInstance = (i3 == 4 || ((i2 & 8) != 0 && composerStartRestartGroup.changedInstance(player))) | composerStartRestartGroup.changedInstance(view);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function1() { // from class: com.stremio.common.composables.PlayerViewKt$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return PlayerViewKt.View$lambda$0$0(player, view, (DisposableEffectScope) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            EffectsKt.DisposableEffect(player, view, (Function1) objRememberedValue, composerStartRestartGroup, i2 & WebSocketProtocol.PAYLOAD_SHORT);
            Integer numValueOf = Integer.valueOf(configuration.orientation);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1866550119, "CC(remember):PlayerView.kt#9igjgp");
            boolean zChangedInstance2 = (i3 == 4 || ((i2 & 8) != 0 && composerStartRestartGroup.changedInstance(player))) | composerStartRestartGroup.changedInstance(view);
            PlayerViewKt$View$2$1 playerViewKt$View$2$1RememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance2 || playerViewKt$View$2$1RememberedValue == Composer.INSTANCE.getEmpty()) {
                playerViewKt$View$2$1RememberedValue = new PlayerViewKt$View$2$1(player, view, null);
                composerStartRestartGroup.updateRememberedValue(playerViewKt$View$2$1RememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            EffectsKt.LaunchedEffect(numValueOf, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) playerViewKt$View$2$1RememberedValue, composerStartRestartGroup, 0);
            Modifier modifierM342backgroundbw27NRU$default = BackgroundKt.m342backgroundbw27NRU$default(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), Color.INSTANCE.m5594getBlack0d7_KjU(), null, 2, null);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1866544742, "CC(remember):PlayerView.kt#9igjgp");
            boolean zChangedInstance3 = composerStartRestartGroup.changedInstance(view);
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance3 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = new Function1() { // from class: com.stremio.common.composables.PlayerViewKt$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return PlayerViewKt.View$lambda$2$0(view, (Context) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            AndroidView_androidKt.AndroidView((Function1) objRememberedValue2, modifierM342backgroundbw27NRU$default, null, composerStartRestartGroup, 48, 4);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.stremio.common.composables.PlayerViewKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return PlayerViewKt.View$lambda$3(player, view, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DisposableEffectResult View$lambda$0$0(final Player player, final View view, DisposableEffectScope DisposableEffect) {
        Intrinsics.checkNotNullParameter(DisposableEffect, "$this$DisposableEffect");
        player.attachView(view);
        return new DisposableEffectResult() { // from class: com.stremio.common.composables.PlayerViewKt$View$lambda$0$0$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                player.detachView(view);
            }
        };
    }

    private static final PlayerView createExoPlayerView(Context context, Profile.Settings settings) {
        PlayerView playerView = new PlayerView(context);
        playerView.setShowBuffering(0);
        playerView.setUseController(false);
        playerView.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        SubtitleView subtitleView = playerView.getSubtitleView();
        ViewParent parent = subtitleView != null ? subtitleView.getParent() : null;
        ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
        if (viewGroup != null && !Intrinsics.areEqual(viewGroup, playerView)) {
            SubtitleView subtitleView2 = subtitleView;
            viewGroup.removeView(subtitleView2);
            playerView.addView(subtitleView2);
        }
        SubtitleView subtitleView3 = playerView.getSubtitleView();
        if (subtitleView3 != null) {
            PlayerExtKt.stylizeView(subtitleView3, settings);
        }
        return playerView;
    }

    private static final VLCVideoLayout createVLCPlayerView(Context context) {
        VLCVideoLayout vLCVideoLayout = new VLCVideoLayout(context);
        vLCVideoLayout.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        return vLCVideoLayout;
    }

    private static final SurfaceView createMPVPlayerView(Context context) {
        SurfaceView surfaceView = new SurfaceView(context);
        surfaceView.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        return surfaceView;
    }

    private static final FrameLayout createYouTubePlayerView(Context context) {
        FrameLayout frameLayout = new FrameLayout(context);
        frameLayout.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        return frameLayout;
    }
}