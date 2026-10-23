package com.stremio.tv.views.player.menus;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.media3.exoplayer.RendererCapabilities;
import com.stremio.common.extensions.PlayerExtKt;
import com.stremio.common.translations.StringsKt;
import com.stremio.common.viewmodels.state.PlayerType;
import com.stremio.translations.Strings;
import com.stremio.tv.composables.MenuItem;
import com.stremio.tv.composables.MenuKt;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlayerMenu.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aK\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u00022\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00060\u000bH\u0007b\u0002\b\rb\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010¢\u0006\u0002\u0010\f\"\u0017\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0011"}, d2 = {"PLAYERS", "", "Lcom/stremio/common/viewmodels/state/PlayerType;", "getPLAYERS", "()Ljava/util/List;", "PlayerMenu", "", "selected", "onPlayerChanged", "Lkotlin/Function1;", "onClose", "Lkotlin/Function0;", "(Lcom/stremio/common/viewmodels/state/PlayerType;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "Landroidx/compose/runtime/Composable;", "Landroidx/compose/runtime/ComposableTarget;", "applier", "androidx.compose.ui.UiComposable", "Stremio:androidTV-com.stremio.one-1.11.2-40000002_release"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class PlayerMenuKt {
    private static final List<PlayerType> PLAYERS = CollectionsKt.listOf((Object[]) new PlayerType[]{PlayerType.EXO_PLAYER, PlayerType.LIBVLC, PlayerType.MPV, PlayerType.EXTERNAL});

    static final Unit PlayerMenu$lambda$2(PlayerType playerType, Function1 function1, Function0 function0, int i, Composer composer, int i2) {
        PlayerMenu(playerType, function1, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static final List<PlayerType> getPLAYERS() {
        return PLAYERS;
    }

    public static final void PlayerMenu(final PlayerType playerType, final Function1<? super PlayerType, Unit> onPlayerChanged, final Function0<Unit> onClose, Composer composer, final int i) {
        Intrinsics.checkNotNullParameter(onPlayerChanged, "onPlayerChanged");
        Intrinsics.checkNotNullParameter(onClose, "onClose");
        Composer composerStartRestartGroup = composer.startRestartGroup(-1956822705);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(PlayerMenu)N(selected,onPlayerChanged,onClose)24@688L7,26@713L269,41@1075L164,37@988L251:PlayerMenu.kt#va7op5");
        int i2 = (i & 6) == 0 ? (composerStartRestartGroup.changed(playerType == null ? -1 : playerType.ordinal()) ? 4 : 2) | i : i;
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(onPlayerChanged) ? 32 : 16;
        }
        if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(onClose) ? 256 : 128;
        }
        if (!composerStartRestartGroup.shouldExecute((i2 & 147) != 146, i2 & 1)) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1956822705, i2, -1, "com.stremio.tv.views.player.menus.PlayerMenu (PlayerMenu.kt:23)");
            }
            ProvidableCompositionLocal<Strings> localStrings = StringsKt.getLocalStrings();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = composerStartRestartGroup.consume(localStrings);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Strings strings = (Strings) objConsume;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 689061884, "CC(remember):PlayerMenu.kt#9igjgp");
            boolean z = (i2 & 14) == 4;
            int i3 = i2 & 896;
            boolean z2 = z | (i3 == 256);
            ArrayList arrayListRememberedValue = composerStartRestartGroup.rememberedValue();
            if (z2 || arrayListRememberedValue == Composer.INSTANCE.getEmpty()) {
                List<PlayerType> list = PLAYERS;
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                for (PlayerType playerType2 : list) {
                    arrayList.add(new MenuItem("player_" + playerType2, PlayerExtKt.toDisplayName(playerType2), playerType2, null, null, playerType2 == playerType, 24, null));
                }
                arrayListRememberedValue = arrayList;
                composerStartRestartGroup.updateRememberedValue(arrayListRememberedValue);
            }
            final List list2 = (List) arrayListRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            MenuKt.Menu(strings.getLabel_settings_nav_player(), 0.25f, onClose, ComposableLambdaKt.rememberComposableLambda(246928894, true, new Function2() { // from class: com.stremio.tv.views.player.menus.PlayerMenuKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return PlayerMenuKt.PlayerMenu$lambda$1(list2, onPlayerChanged, onClose, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, composerStartRestartGroup, 54), composerStartRestartGroup, i3 | 3120, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.stremio.tv.views.player.menus.PlayerMenuKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return PlayerMenuKt.PlayerMenu$lambda$2(playerType, onPlayerChanged, onClose, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit PlayerMenu$lambda$1(List list, final Function1 function1, final Function0 function0, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C44@1145L77,42@1085L148:PlayerMenu.kt#va7op5");
        if (!composer.shouldExecute((i & 3) != 2, i & 1)) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(246928894, i, -1, "com.stremio.tv.views.player.menus.PlayerMenu.<anonymous> (PlayerMenu.kt:42)");
            }
            ComposerKt.sourceInformationMarkerStart(composer, 1075468171, "CC(remember):PlayerMenu.kt#9igjgp");
            boolean zChanged = composer.changed(function1) | composer.changed(function0);
            Object objRememberedValue = composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function1() { // from class: com.stremio.tv.views.player.menus.PlayerMenuKt$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return PlayerMenuKt.PlayerMenu$lambda$1$0$0(function1, function0, (PlayerType) obj);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            MenuKt.MenuList(null, list, (Function1) objRememberedValue, composer, 0, 1);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit PlayerMenu$lambda$1$0$0(Function1 function1, Function0 function0, PlayerType it) {
        Intrinsics.checkNotNullParameter(it, "it");
        function1.invoke(it);
        function0.invoke();
        return Unit.INSTANCE;
    }
}