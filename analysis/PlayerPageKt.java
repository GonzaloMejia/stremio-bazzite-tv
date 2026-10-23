package com.stremio.tv.views.player;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import androidx.activity.compose.BackHandlerKt;
import androidx.activity.compose.LocalActivityKt;
import androidx.activity.compose.ManagedActivityResultLauncher;
import androidx.compose.foundation.FocusableKt;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.style.StylePropertiesKt;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.input.key.Key;
import androidx.compose.ui.input.key.KeyEvent;
import androidx.compose.ui.input.key.KeyEventType;
import androidx.compose.ui.input.key.KeyEvent_androidKt;
import androidx.compose.ui.input.key.KeyInputModifierKt;
import androidx.compose.ui.input.pointer.PointerInputEventHandler;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.compose.LifecycleEffectKt;
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import com.stremio.common.composables.IntroOutroPopupKt;
import com.stremio.common.composables.IntroOutroType;
import com.stremio.common.composables.PlayerLoadingKt;
import com.stremio.common.composables.PlayerViewKt;
import com.stremio.common.composables.animations.FadeInOutKt;
import com.stremio.common.extensions.ActivityKt;
import com.stremio.common.extensions.ModifierExtKt;
import com.stremio.common.extensions.PlayerExtKt;
import com.stremio.common.extensions.ProfileExtKt;
import com.stremio.common.platform.MediaInfoResult;
import com.stremio.common.players.Chapter;
import com.stremio.common.players.ExternalPlayerContract;
import com.stremio.common.players.FrameRateMatcher;
import com.stremio.common.players.PlaybackListener;
import com.stremio.common.players.PlaybackManager;
import com.stremio.common.players.PlaybackState;
import com.stremio.common.players.Player;
import com.stremio.common.players.Track;
import com.stremio.common.translations.StringsKt;
import com.stremio.common.utils.BinaryStateKt;
import com.stremio.common.utils.DeeplinkUtil;
import com.stremio.common.utils.Immersion;
import com.stremio.common.utils.PlayerKt;
import com.stremio.common.viewmodels.PlayerViewModel;
import com.stremio.common.viewmodels.PreferencesState;
import com.stremio.common.viewmodels.PreferencesViewModel;
import com.stremio.common.viewmodels.ServerViewModel;
import com.stremio.common.viewmodels.SettingsViewModel;
import com.stremio.common.viewmodels.state.PlayerState;
import com.stremio.common.viewmodels.state.PlayerType;
import com.stremio.common.viewmodels.state.ServerState;
import com.stremio.common.viewmodels.state.SkipSegment;
import com.stremio.common.viewmodels.state.VideoPlaybackState;
import com.stremio.core.models.LoadableStatistics;
import com.stremio.core.types.profile.Auth;
import com.stremio.core.types.profile.Profile;
import com.stremio.core.types.resource.MetaItem;
import com.stremio.core.types.resource.Stream;
import com.stremio.core.types.resource.Video;
import com.stremio.core.types.subtitle.Subtitle;
import com.stremio.icons.Icons;
import com.stremio.translations.Strings;
import com.stremio.tv.Constants;
import com.stremio.tv.R;
import com.stremio.tv.ThemeKt;
import com.stremio.tv.composables.ButtonKt;
import com.stremio.tv.composables.ModalKt;
import com.stremio.tv.views.player.menus.AudioMenuKt;
import com.stremio.tv.views.player.menus.ChaptersMenuKt;
import com.stremio.tv.views.player.menus.PlayerMenuKt;
import com.stremio.tv.views.player.menus.SpeedMenuKt;
import com.stremio.tv.views.player.menus.StatisticsMenuKt;
import com.stremio.tv.views.player.menus.SubtitlesMenuKt;
import com.stremio.tv.views.player.menus.VideoMenuKt;
import com.stremio.tv.views.player.overlay.OverlayKt;
import com.stremio.tv.views.player.overlay.OverlaySettingKt;
import com.stremio.tv.views.settings.Tabs;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Triple;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.CoroutineScope;
import org.koin.compose.KoinApplicationKt;
import org.koin.viewmodel.CreationExtrasExtKt;
import org.koin.viewmodel.GetViewModelKt;

/* JADX INFO: compiled from: PlayerPage.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000P\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u001a=\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u0007H\u0007b\u0002\b\tb\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f¢\u0006\u0002\u0010\b¨\u0006\r²\u0006\n\u0010\u000e\u001a\u00020\u000fX\u008a\u008e\u0002²\u0006\n\u0010\u0010\u001a\u00020\u0011X\u008a\u0084\u0002²\u0006\n\u0010\u0012\u001a\u00020\u0013X\u008a\u0084\u0002²\u0006\f\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u008a\u0084\u0002²\u0006\n\u0010\u0016\u001a\u00020\u0017X\u008a\u0084\u0002²\u0006\n\u0010\u0018\u001a\u00020\u0019X\u008a\u0084\u0002²\u0006\n\u0010\u001a\u001a\u00020\u001bX\u008a\u008e\u0002²\u0006\n\u0010\u001c\u001a\u00020\u001bX\u008a\u008e\u0002"}, d2 = {"PlayerPage", "", "navController", "Landroidx/navigation/NavController;", "args", "Lcom/stremio/tv/views/player/PlayerFragmentArgs;", "onBack", "Lkotlin/Function0;", "(Landroidx/navigation/NavController;Lcom/stremio/tv/views/player/PlayerFragmentArgs;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "Landroidx/compose/runtime/Composable;", "Landroidx/compose/runtime/ComposableTarget;", "applier", "androidx.compose.ui.UiComposable", "Stremio:androidTV-com.stremio.one-1.11.2-40000002_release", "defaultFocusRequester", "Landroidx/compose/ui/focus/FocusRequester;", "playerState", "Lcom/stremio/common/viewmodels/state/PlayerState;", "serverState", "Lcom/stremio/common/viewmodels/state/ServerState;", "authState", "Lcom/stremio/core/types/profile/Auth;", "preferences", "Lcom/stremio/common/viewmodels/PreferencesState;", "playbackState", "Lcom/stremio/common/players/PlaybackState;", "nextVideoPopupDismissed", "", "showServerBackgroundModal"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class PlayerPageKt {

    /* JADX INFO: compiled from: PlayerPage.kt */
    @Metadata(k = 3, mv = {2, 4, 0}, xi = 1328)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[Menu.values().length];
            try {
                iArr[Menu.SUBTITLES.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Menu.AUDIO.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[Menu.SPEED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[Menu.PLAYERS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[Menu.CHAPTERS.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[Menu.VIDEOS.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[Menu.STATISTICS.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[IntroOutroType.values().length];
            try {
                iArr2[IntroOutroType.INTRO.ordinal()] = 1;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr2[IntroOutroType.OUTRO.ordinal()] = 2;
            } catch (NoSuchFieldError unused9) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    static final Unit PlayerPage$lambda$62(NavController navController, PlayerFragmentArgs playerFragmentArgs, Function0 function0, int i, Composer composer, int i2) {
        PlayerPage(navController, playerFragmentArgs, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:129:0x05a1  */
    /* JADX WARN: Code duplicated, block: B:134:0x05c8  */
    /* JADX WARN: Code duplicated, block: B:139:0x05f4  */
    /* JADX WARN: Code duplicated, block: B:144:0x061b  */
    /* JADX WARN: Code duplicated, block: B:149:0x0642  */
    /* JADX WARN: Code duplicated, block: B:154:0x0669  */
    /* JADX WARN: Code duplicated, block: B:159:0x0690  */
    /* JADX WARN: Code duplicated, block: B:164:0x06b7  */
    /* JADX WARN: Code duplicated, block: B:169:0x06de  */
    /* JADX WARN: Code duplicated, block: B:181:0x0767  */
    /* JADX WARN: Code duplicated, block: B:186:0x078c  */
    /* JADX WARN: Code duplicated, block: B:191:0x07b8  */
    /* JADX WARN: Code duplicated, block: B:198:0x07e7  */
    /* JADX WARN: Code duplicated, block: B:203:0x0818  */
    /* JADX WARN: Code duplicated, block: B:208:0x084e  */
    /* JADX WARN: Code duplicated, block: B:215:0x088c  */
    /* JADX WARN: Code duplicated, block: B:220:0x08d8  */
    /* JADX WARN: Code duplicated, block: B:227:0x08f7  */
    /* JADX WARN: Code duplicated, block: B:230:0x0908  */
    /* JADX WARN: Code duplicated, block: B:231:0x090a  */
    /* JADX WARN: Code duplicated, block: B:236:0x091a  */
    /* JADX WARN: Code duplicated, block: B:241:0x095d  */
    /* JADX WARN: Code duplicated, block: B:246:0x0993  */
    /* JADX WARN: Code duplicated, block: B:251:0x09f0  */
    /* JADX WARN: Code duplicated, block: B:254:0x0a17  */
    /* JADX WARN: Code duplicated, block: B:256:0x0a1b  */
    /* JADX WARN: Code duplicated, block: B:259:0x0a24  */
    /* JADX WARN: Code duplicated, block: B:261:0x0a28  */
    /* JADX WARN: Code duplicated, block: B:265:0x0a3c  */
    /* JADX WARN: Code duplicated, block: B:267:0x0a44  */
    /* JADX WARN: Code duplicated, block: B:270:0x0a6c  */
    /* JADX WARN: Code duplicated, block: B:272:0x0a74  */
    /* JADX WARN: Code duplicated, block: B:275:0x0aab  */
    /* JADX WARN: Code duplicated, block: B:277:0x0ab3  */
    /* JADX WARN: Code duplicated, block: B:280:0x0ada  */
    /* JADX WARN: Code duplicated, block: B:282:0x0ae2  */
    /* JADX WARN: Code duplicated, block: B:285:0x0b0f  */
    /* JADX WARN: Code duplicated, block: B:287:0x0b17  */
    /* JADX WARN: Code duplicated, block: B:290:0x0b45  */
    /* JADX WARN: Code duplicated, block: B:292:0x0b4d  */
    /* JADX WARN: Code duplicated, block: B:295:0x0b8d  */
    /* JADX WARN: Code duplicated, block: B:297:0x0b95  */
    /* JADX WARN: Code duplicated, block: B:300:0x0c0b  */
    /* JADX WARN: Code duplicated, block: B:302:0x0c13  */
    /* JADX WARN: Code duplicated, block: B:305:0x0c51  */
    /* JADX WARN: Code duplicated, block: B:307:0x0c59  */
    /* JADX WARN: Code duplicated, block: B:310:0x0cba  */
    /* JADX WARN: Code duplicated, block: B:314:0x0cc6  */
    /* JADX WARN: Code duplicated, block: B:317:0x0d3a  */
    /* JADX WARN: Code duplicated, block: B:321:0x0d4b  */
    /* JADX WARN: Code duplicated, block: B:324:0x0db5  */
    /* JADX WARN: Code duplicated, block: B:328:0x0dc4  */
    /* JADX WARN: Code duplicated, block: B:331:0x0e78  */
    /* JADX WARN: Code duplicated, block: B:337:0x0e90  */
    /* JADX WARN: Code duplicated, block: B:340:0x0eba  */
    /* JADX WARN: Code duplicated, block: B:342:0x0ec2  */
    /* JADX WARN: Code duplicated, block: B:345:0x0f16  */
    /* JADX WARN: Code duplicated, block: B:348:0x0f22  */
    /* JADX WARN: Code duplicated, block: B:349:0x0f26  */
    /* JADX WARN: Code duplicated, block: B:352:0x0fc2  */
    /* JADX WARN: Code duplicated, block: B:353:0x1039  */
    /* JADX WARN: Code duplicated, block: B:355:0x1046  */
    /* JADX WARN: Code duplicated, block: B:356:0x1086  */
    /* JADX WARN: Code duplicated, block: B:358:0x1091  */
    /* JADX WARN: Code duplicated, block: B:359:0x10b8  */
    /* JADX WARN: Code duplicated, block: B:361:0x10c3  */
    /* JADX WARN: Code duplicated, block: B:362:0x10df  */
    /* JADX WARN: Code duplicated, block: B:364:0x10ea  */
    /* JADX WARN: Code duplicated, block: B:365:0x1113  */
    /* JADX WARN: Code duplicated, block: B:367:0x111e  */
    /* JADX WARN: Code duplicated, block: B:368:0x114b  */
    /* JADX WARN: Code duplicated, block: B:370:0x1156  */
    /* JADX WARN: Code duplicated, block: B:371:0x1172  */
    /* JADX WARN: Code duplicated, block: B:374:0x125e  */
    /* JADX WARN: Code duplicated, block: B:376:0x12af  */
    /* JADX WARN: Code duplicated, block: B:378:0x12f0  */
    /* JADX WARN: Code duplicated, block: B:381:0x12ff  */
    /* JADX WARN: Code duplicated, block: B:383:0x131c  */
    /* JADX WARN: Code duplicated, block: B:385:0x134a  */
    /* JADX WARN: Code duplicated, block: B:388:0x1359  */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public static final void PlayerPage(final NavController navController, final PlayerFragmentArgs args, final Function0<Unit> onBack, Composer composer, final int i) {
        int i2;
        int i3;
        boolean zChangedInstance;
        Object objRememberedValue;
        boolean zChangedInstance2;
        Object objRememberedValue2;
        Function1 function1;
        boolean zChangedInstance3;
        Object objRememberedValue3;
        boolean zChangedInstance4;
        Object objRememberedValue4;
        boolean zChangedInstance5;
        Object objRememberedValue5;
        boolean zChangedInstance6;
        Object objRememberedValue6;
        boolean zChangedInstance7;
        Object objRememberedValue7;
        boolean zChangedInstance8;
        Object objRememberedValue8;
        Function1 function2;
        boolean zChangedInstance9;
        Object objRememberedValue9;
        Function1 function3;
        boolean zChanged;
        Object objRememberedValue10;
        boolean zChangedInstance10;
        Object objRememberedValue11;
        Function1 function4;
        boolean zChangedInstance11;
        Object objRememberedValue12;
        Function1 function5;
        boolean zChanged2;
        Object objRememberedValue13;
        Function1 function6;
        boolean zChanged3;
        Object objRememberedValue14;
        final MutableState mutableState;
        boolean zChanged4;
        Object objRememberedValue15;
        Function0 function0;
        boolean zChanged5;
        Object objRememberedValue16;
        Function0 function7;
        boolean zChanged6;
        FocusRequester focusRequester;
        Function0 function8;
        final Immersion immersion;
        MutableState mutableState2;
        State state;
        Object obj;
        ManagedActivityResultLauncher<ExternalPlayerContract.ExternalPlayerInput, ExternalPlayerContract.ExternalPlayerResult> managedActivityResultLauncherCreateExternalPlayer;
        boolean zChangedInstance12;
        Object objRememberedValue17;
        Function0 function9;
        boolean z;
        boolean z2;
        boolean z3;
        Object objRememberedValue18;
        boolean zChanged7;
        PlayerPageKt$PlayerPage$2$1 playerPageKt$PlayerPage$2$1RememberedValue;
        boolean zChangedInstance13;
        Object objRememberedValue19;
        Immersion immersion2;
        MutableState mutableState3;
        boolean z4;
        boolean z5;
        boolean zChangedInstance14;
        PlayerPageKt$PlayerPage$4$1 playerPageKt$PlayerPage$4$1RememberedValue;
        final PlayerFragmentArgs playerFragmentArgs;
        boolean z6;
        boolean zChangedInstance15;
        Object objRememberedValue20;
        boolean zChangedInstance16;
        PlayerPageKt$PlayerPage$6$1 playerPageKt$PlayerPage$6$1RememberedValue;
        boolean zChangedInstance17;
        PlayerPageKt$PlayerPage$7$1 playerPageKt$PlayerPage$7$1RememberedValue;
        boolean zChangedInstance18;
        Object objRememberedValue21;
        boolean zChangedInstance19;
        PlayerPageKt$PlayerPage$9$1 playerPageKt$PlayerPage$9$1RememberedValue;
        boolean zChangedInstance20;
        PlayerPageKt$PlayerPage$10$1 playerPageKt$PlayerPage$10$1RememberedValue;
        boolean zChangedInstance21;
        PlayerPageKt$PlayerPage$11$1 playerPageKt$PlayerPage$11$1RememberedValue;
        boolean zChanged8;
        PlayerPageKt$PlayerPage$12$1 playerPageKt$PlayerPage$12$1RememberedValue;
        boolean zChanged9;
        PlayerPageKt$PlayerPage$13$1 playerPageKt$PlayerPage$13$1RememberedValue;
        boolean zChanged10;
        PlayerPageKt$PlayerPage$14$1 playerPageKt$PlayerPage$14$1RememberedValue;
        Profile.Settings settings;
        boolean zChanged11;
        PlayerPageKt$PlayerPage$15$1 playerPageKt$PlayerPage$15$1RememberedValue;
        State state2;
        final State state3;
        MutableState mutableState4;
        final Strings strings;
        boolean zChanged12;
        PlayerPageKt$PlayerPage$16$1 playerPageKt$PlayerPage$16$1RememberedValue;
        boolean z7;
        Immersion immersion3;
        State state4;
        Immersion immersion4;
        final MutableState mutableState5;
        Composer composer2;
        boolean z8;
        boolean zChangedInstance22;
        PlayerPageKt$PlayerPage$17$1 playerPageKt$PlayerPage$17$1RememberedValue;
        Function0<ComposeUiNode> constructor;
        final State state5;
        final Profile.Settings settings2;
        final State state6;
        int i4;
        String str;
        Object objRememberedValue22;
        Object objRememberedValue23;
        Intrinsics.checkNotNullParameter(navController, "navController");
        Intrinsics.checkNotNullParameter(args, "args");
        Intrinsics.checkNotNullParameter(onBack, "onBack");
        Composer composerStartRestartGroup = composer.startRestartGroup(1542025864);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(PlayerPage)N(navController,args,onBack)107@4351L7,108@4392L7,109@4431L7,110@4486L7,112@4538L15,113@4597L15,114@4660L15,115@4729L15,117@4780L29,118@4842L29,119@4905L52,121@5004L16,122@5066L16,123@5132L16,124@5199L16,129@5336L51,159@6385L106,162@6539L16,165@6617L19,167@6707L19,168@6784L19,169@6861L19,170@6941L19,171@7027L19,172@7104L19,173@7196L19,178@7467L19,179@7577L19,180@7693L19,181@7794L19,186@8028L19,187@8083L34,191@8272L34,193@8324L107,197@8467L127,204@8629L171,211@8833L34,215@8913L35,219@8992L48,223@9079L73,228@9206L57,232@9316L56,236@9427L58,240@9535L53,244@9637L52,248@9739L53,252@9831L359,264@10240L49,268@10337L45,272@10426L35,276@10507L76,281@10619L111,287@10766L196,296@11008L755,320@11790L57,322@11886L163,329@12126L119,329@12055L190,337@12312L192,337@12251L253,345@12556L72,345@12510L118,350@12671L64,350@12634L101,354@12764L300,354@12741L323,367@13093L250,367@13070L273,380@13395L288,380@13349L334,390@13712L87,390@13689L110,396@13851L79,396@13805L125,400@13957L361,400@13936L382,411@14372L1443,411@14324L1491,455@16052L1157,448@15849L1360,487@17250L218,487@17215L253,495@17586L691,495@17474L803,515@18379L1221,515@18283L1317,540@19667L220,540@19606L281,550@19893L71,564@20377L16,555@19970L430,567@20406L123,576@20597L98,579@20804L108,573@20535L2168,697@25367L339,708@25712L437,721@26155L427,734@26588L323,745@26917L266:PlayerPage.kt#3vay5t");
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changedInstance(navController) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= (i & 64) == 0 ? composerStartRestartGroup.changed(args) : composerStartRestartGroup.changedInstance(args) ? 32 : 16;
        }
        if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(onBack) ? 256 : 128;
        }
        int i5 = i2;
        if (!composerStartRestartGroup.shouldExecute((i5 & 147) != 146, i5 & 1)) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1542025864, i5, -1, "com.stremio.tv.views.player.PlayerPage (PlayerPage.kt:106)");
            }
            ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = composerStartRestartGroup.consume(localContext);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Context context = (Context) objConsume;
            ProvidableCompositionLocal<Activity> localActivity = LocalActivityKt.getLocalActivity();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume2 = composerStartRestartGroup.consume(localActivity);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final Activity activity = (Activity) objConsume2;
            ProvidableCompositionLocal<Strings> localStrings = StringsKt.getLocalStrings();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume3 = composerStartRestartGroup.consume(localStrings);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Strings strings2 = (Strings) objConsume3;
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume4 = composerStartRestartGroup.consume(localLayoutDirection);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            LayoutDirection layoutDirection = (LayoutDirection) objConsume4;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -924953623, "CC(koinViewModel)N(qualifier,viewModelStoreOwner,key,extras,scope,parameters)48@1587L7,51@1782L18:ViewModel.kt#7bazx");
            ViewModelStoreOwner current = LocalViewModelStoreOwner.INSTANCE.getCurrent(composerStartRestartGroup, LocalViewModelStoreOwner.$stable);
            if (current == null) {
                throw new IllegalStateException("No ViewModelStoreOwner was provided via LocalViewModelStoreOwner".toString());
            }
            ViewModel viewModelResolveViewModel = GetViewModelKt.resolveViewModel(Reflection.getOrCreateKotlinClass(PlayerViewModel.class), current.getViewModelStore(), null, CreationExtrasExtKt.defaultExtras(current), null, KoinApplicationKt.currentKoinScope(composerStartRestartGroup, 0), null);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final PlayerViewModel playerViewModel = (PlayerViewModel) viewModelResolveViewModel;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -924953623, "CC(koinViewModel)N(qualifier,viewModelStoreOwner,key,extras,scope,parameters)48@1587L7,51@1782L18:ViewModel.kt#7bazx");
            ViewModelStoreOwner current2 = LocalViewModelStoreOwner.INSTANCE.getCurrent(composerStartRestartGroup, LocalViewModelStoreOwner.$stable);
            if (current2 == null) {
                throw new IllegalStateException("No ViewModelStoreOwner was provided via LocalViewModelStoreOwner".toString());
            }
            ViewModel viewModelResolveViewModel2 = GetViewModelKt.resolveViewModel(Reflection.getOrCreateKotlinClass(ServerViewModel.class), current2.getViewModelStore(), null, CreationExtrasExtKt.defaultExtras(current2), null, KoinApplicationKt.currentKoinScope(composerStartRestartGroup, 0), null);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ServerViewModel serverViewModel = (ServerViewModel) viewModelResolveViewModel2;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -924953623, "CC(koinViewModel)N(qualifier,viewModelStoreOwner,key,extras,scope,parameters)48@1587L7,51@1782L18:ViewModel.kt#7bazx");
            ViewModelStoreOwner current3 = LocalViewModelStoreOwner.INSTANCE.getCurrent(composerStartRestartGroup, LocalViewModelStoreOwner.$stable);
            if (current3 == null) {
                throw new IllegalStateException("No ViewModelStoreOwner was provided via LocalViewModelStoreOwner".toString());
            }
            ViewModel viewModelResolveViewModel3 = GetViewModelKt.resolveViewModel(Reflection.getOrCreateKotlinClass(SettingsViewModel.class), current3.getViewModelStore(), null, CreationExtrasExtKt.defaultExtras(current3), null, KoinApplicationKt.currentKoinScope(composerStartRestartGroup, 0), null);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            SettingsViewModel settingsViewModel = (SettingsViewModel) viewModelResolveViewModel3;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -924953623, "CC(koinViewModel)N(qualifier,viewModelStoreOwner,key,extras,scope,parameters)48@1587L7,51@1782L18:ViewModel.kt#7bazx");
            ViewModelStoreOwner current4 = LocalViewModelStoreOwner.INSTANCE.getCurrent(composerStartRestartGroup, LocalViewModelStoreOwner.$stable);
            if (current4 == null) {
                throw new IllegalStateException("No ViewModelStoreOwner was provided via LocalViewModelStoreOwner".toString());
            }
            ViewModel viewModelResolveViewModel4 = GetViewModelKt.resolveViewModel(Reflection.getOrCreateKotlinClass(PreferencesViewModel.class), current4.getViewModelStore(), null, CreationExtrasExtKt.defaultExtras(current4), null, KoinApplicationKt.currentKoinScope(composerStartRestartGroup, 0), null);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            PreferencesViewModel preferencesViewModel = (PreferencesViewModel) viewModelResolveViewModel4;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 123156389, "CC(remember):PlayerPage.kt#9igjgp");
            Object objRememberedValue24 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue24 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue24 = new FocusRequester();
                composerStartRestartGroup.updateRememberedValue(objRememberedValue24);
            }
            final FocusRequester focusRequester2 = (FocusRequester) objRememberedValue24;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 123158373, "CC(remember):PlayerPage.kt#9igjgp");
            Object objRememberedValue25 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue25 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue25 = new FocusRequester();
                composerStartRestartGroup.updateRememberedValue(objRememberedValue25);
            }
            final FocusRequester focusRequester3 = (FocusRequester) objRememberedValue25;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 123160412, "CC(remember):PlayerPage.kt#9igjgp");
            Object objRememberedValue26 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue26 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue26 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(focusRequester2, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue26);
            }
            final MutableState mutableState6 = (MutableState) objRememberedValue26;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final State stateCollectAsState = SnapshotStateKt.collectAsState(playerViewModel.getState(), null, composerStartRestartGroup, 0, 1);
            final State stateCollectAsState2 = SnapshotStateKt.collectAsState(serverViewModel.getState(), null, composerStartRestartGroup, 0, 1);
            State stateCollectAsState3 = SnapshotStateKt.collectAsState(settingsViewModel.getAuthState(), null, composerStartRestartGroup, 0, 1);
            State stateCollectAsState4 = SnapshotStateKt.collectAsState(preferencesViewModel.getState(), null, composerStartRestartGroup, 0, 1);
            final Profile.Settings settings3 = playerViewModel.getSettings();
            Auth authPlayerPage$lambda$7 = PlayerPage$lambda$7(stateCollectAsState3);
            final boolean zIsPremium = ProfileExtKt.isPremium(authPlayerPage$lambda$7 != null ? authPlayerPage$lambda$7.getUser() : null);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 123174203, "CC(remember):PlayerPage.kt#9igjgp");
            Object objRememberedValue27 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue27 == Composer.INSTANCE.getEmpty()) {
                Intrinsics.checkNotNull(activity);
                objRememberedValue27 = new FrameRateMatcher(activity, settings3);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue27);
            }
            final FrameRateMatcher frameRateMatcher = (FrameRateMatcher) objRememberedValue27;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            PlaybackListener playbackListener = new PlaybackListener() { // from class: com.stremio.tv.views.player.PlayerPageKt$PlayerPage$playbackListener$1
                @Override // com.stremio.common.players.PlaybackListener
                public /* bridge */ void onChanged(Player player) {
                    super.onChanged(player);
                }

                @Override // com.stremio.common.players.PlaybackListener
                public void onEnded(boolean unsupported) {
                    if (!unsupported && settings3.getBingeWatching() && PlayerPageKt.PlayerPage$lambda$5(stateCollectAsState).getBingeVideo() != null) {
                        NavController navController2 = navController;
                        State<PlayerState> state7 = stateCollectAsState;
                        Video bingeVideo = PlayerPageKt.PlayerPage$lambda$5(state7).getBingeVideo();
                        Intrinsics.checkNotNull(bingeVideo);
                        PlayerPageKt.PlayerPage$navigateToVideo(navController2, state7, bingeVideo);
                        return;
                    }
                    onBack.invoke();
                }
            };
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 123207826, "CC(remember):PlayerPage.kt#9igjgp");
            Object objRememberedValue28 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue28 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue28 = new PlaybackManager(playbackListener, context, playerViewModel, preferencesViewModel);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue28);
            }
            final PlaybackManager playbackManager = (PlaybackManager) objRememberedValue28;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final State stateCollectAsState5 = SnapshotStateKt.collectAsState(playbackManager.getState(), null, composerStartRestartGroup, 0, 1);
            final Player player = playbackManager.getPlayer();
            final Immersion immersionRememberImmersion = PlayerKt.rememberImmersion(composerStartRestartGroup, 0);
            Triple<Boolean, Function0<Unit>, Function0<Unit>> tripleCreateBinaryState = BinaryStateKt.createBinaryState(composerStartRestartGroup, 0);
            boolean zBooleanValue = tripleCreateBinaryState.component1().booleanValue();
            final Function0<Unit> function0Component2 = tripleCreateBinaryState.component2();
            Function0<Unit> function0Component3 = tripleCreateBinaryState.component3();
            Triple<Boolean, Function0<Unit>, Function0<Unit>> tripleCreateBinaryState2 = BinaryStateKt.createBinaryState(composerStartRestartGroup, 0);
            boolean zBooleanValue2 = tripleCreateBinaryState2.component1().booleanValue();
            final Function0<Unit> function0Component4 = tripleCreateBinaryState2.component2();
            Function0<Unit> function0Component5 = tripleCreateBinaryState2.component3();
            Triple<Boolean, Function0<Unit>, Function0<Unit>> tripleCreateBinaryState3 = BinaryStateKt.createBinaryState(composerStartRestartGroup, 0);
            boolean zBooleanValue3 = tripleCreateBinaryState3.component1().booleanValue();
            final Function0<Unit> function0Component6 = tripleCreateBinaryState3.component2();
            Function0<Unit> function0Component7 = tripleCreateBinaryState3.component3();
            Triple<Boolean, Function0<Unit>, Function0<Unit>> tripleCreateBinaryState4 = BinaryStateKt.createBinaryState(composerStartRestartGroup, 0);
            boolean zBooleanValue4 = tripleCreateBinaryState4.component1().booleanValue();
            final Function0<Unit> function0Component8 = tripleCreateBinaryState4.component2();
            Function0<Unit> function0Component9 = tripleCreateBinaryState4.component3();
            Triple<Boolean, Function0<Unit>, Function0<Unit>> tripleCreateBinaryState5 = BinaryStateKt.createBinaryState(composerStartRestartGroup, 0);
            boolean zBooleanValue5 = tripleCreateBinaryState5.component1().booleanValue();
            final Function0<Unit> function0Component10 = tripleCreateBinaryState5.component2();
            Function0<Unit> function0Component11 = tripleCreateBinaryState5.component3();
            Triple<Boolean, Function0<Unit>, Function0<Unit>> tripleCreateBinaryState6 = BinaryStateKt.createBinaryState(composerStartRestartGroup, 0);
            boolean zBooleanValue6 = tripleCreateBinaryState6.component1().booleanValue();
            final Function0<Unit> function0Component12 = tripleCreateBinaryState6.component2();
            Function0<Unit> function0Component13 = tripleCreateBinaryState6.component3();
            Triple<Boolean, Function0<Unit>, Function0<Unit>> tripleCreateBinaryState7 = BinaryStateKt.createBinaryState(composerStartRestartGroup, 0);
            boolean zBooleanValue7 = tripleCreateBinaryState7.component1().booleanValue();
            final Function0<Unit> function0Component14 = tripleCreateBinaryState7.component2();
            Function0<Unit> function0Component15 = tripleCreateBinaryState7.component3();
            boolean z9 = zBooleanValue || zBooleanValue2 || zBooleanValue3 || zBooleanValue5 || zBooleanValue6 || zBooleanValue7 || zBooleanValue4;
            Triple<Boolean, Function0<Unit>, Function0<Unit>> tripleCreateBinaryState8 = BinaryStateKt.createBinaryState(composerStartRestartGroup, 0);
            boolean zBooleanValue8 = tripleCreateBinaryState8.component1().booleanValue();
            Function0<Unit> function0Component16 = tripleCreateBinaryState8.component2();
            Function0<Unit> function0Component17 = tripleCreateBinaryState8.component3();
            Triple<Boolean, Function0<Unit>, Function0<Unit>> tripleCreateBinaryState9 = BinaryStateKt.createBinaryState(composerStartRestartGroup, 0);
            boolean zBooleanValue9 = tripleCreateBinaryState9.component1().booleanValue();
            Function0<Unit> function0Component18 = tripleCreateBinaryState9.component2();
            Function0<Unit> function0Component19 = tripleCreateBinaryState9.component3();
            Triple<Boolean, Function0<Unit>, Function0<Unit>> tripleCreateBinaryState10 = BinaryStateKt.createBinaryState(composerStartRestartGroup, 0);
            boolean zBooleanValue10 = tripleCreateBinaryState10.component1().booleanValue();
            Function0<Unit> function0Component20 = tripleCreateBinaryState10.component2();
            Function0<Unit> function0Component21 = tripleCreateBinaryState10.component3();
            Triple<Boolean, Function0<Unit>, Function0<Unit>> tripleCreateBinaryState11 = BinaryStateKt.createBinaryState(composerStartRestartGroup, 0);
            boolean zBooleanValue11 = tripleCreateBinaryState11.component1().booleanValue();
            Function0<Unit> function0Component22 = tripleCreateBinaryState11.component2();
            Function0<Unit> function0Component23 = tripleCreateBinaryState11.component3();
            boolean z10 = zBooleanValue8 || zBooleanValue9 || zBooleanValue10 || zBooleanValue11;
            Triple<Boolean, Function0<Unit>, Function0<Unit>> tripleCreateBinaryState12 = BinaryStateKt.createBinaryState(composerStartRestartGroup, 0);
            boolean zBooleanValue12 = tripleCreateBinaryState12.component1().booleanValue();
            Function0<Unit> function0Component24 = tripleCreateBinaryState12.component2();
            final Function0<Unit> function0Component25 = tripleCreateBinaryState12.component3();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 123262090, "CC(remember):PlayerPage.kt#9igjgp");
            Object objRememberedValue29 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue29 == Composer.INSTANCE.getEmpty()) {
                MutableState mutableStateMutableStateOf$default = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default);
                objRememberedValue29 = mutableStateMutableStateOf$default;
            }
            MutableState mutableState7 = (MutableState) objRememberedValue29;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            boolean z11 = !immersionRememberImmersion.getReady() && PlayerPage$lambda$11(stateCollectAsState5).getError() == null && !z10 && PlayerPage$lambda$11(stateCollectAsState5).getLoaded();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 123268138, "CC(remember):PlayerPage.kt#9igjgp");
            Object objRememberedValue30 = composerStartRestartGroup.rememberedValue();
            boolean z12 = z11;
            if (objRememberedValue30 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue30 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue30);
            }
            MutableState mutableState8 = (MutableState) objRememberedValue30;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            MetaItem metaItem = PlayerPage$lambda$5(stateCollectAsState).getMetaItem();
            Video currentVideo = PlayerPage$lambda$5(stateCollectAsState).getCurrentVideo();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 123269875, "CC(remember):PlayerPage.kt#9igjgp");
            boolean zChanged13 = composerStartRestartGroup.changed(metaItem) | composerStartRestartGroup.changed(currentVideo);
            Object objRememberedValue31 = composerStartRestartGroup.rememberedValue();
            if (zChanged13 || objRememberedValue31 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue31 = PlayerExtKt.title(PlayerPage$lambda$5(stateCollectAsState), layoutDirection);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue31);
            }
            final String str2 = (String) objRememberedValue31;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 123274471, "CC(remember):PlayerPage.kt#9igjgp");
            boolean zChanged14 = composerStartRestartGroup.changed(stateCollectAsState5) | composerStartRestartGroup.changedInstance(player);
            Object objRememberedValue32 = composerStartRestartGroup.rememberedValue();
            if (zChanged14 || objRememberedValue32 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue32 = new Function0() { // from class: com.stremio.tv.views.player.PlayerPageKt$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return PlayerPageKt.PlayerPage$lambda$19$0(player, stateCollectAsState5);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue32);
            }
            final Function0 function10 = (Function0) objRememberedValue32;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 123279699, "CC(remember):PlayerPage.kt#9igjgp");
            int i6 = i5 & 896;
            boolean zChangedInstance23 = (i6 == 256) | composerStartRestartGroup.changedInstance(playerViewModel) | composerStartRestartGroup.changed(stateCollectAsState) | composerStartRestartGroup.changedInstance(navController);
            Object objRememberedValue33 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance23 || objRememberedValue33 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue33 = new Function0() { // from class: com.stremio.tv.views.player.PlayerPageKt$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return PlayerPageKt.PlayerPage$lambda$20$0(playerViewModel, onBack, stateCollectAsState, navController);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue33);
            }
            final Function0 function11 = (Function0) objRememberedValue33;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 123286090, "CC(remember):PlayerPage.kt#9igjgp");
            boolean zChangedInstance24 = composerStartRestartGroup.changedInstance(player);
            Object objRememberedValue34 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance24) {
                i3 = i5;
            } else {
                i3 = i5;
                if (objRememberedValue34 == Composer.INSTANCE.getEmpty()) {
                }
                final Function0 function12 = (Function0) objRememberedValue34;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 123288651, "CC(remember):PlayerPage.kt#9igjgp");
                zChangedInstance = composerStartRestartGroup.changedInstance(player);
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = new Function1() { // from class: com.stremio.tv.views.player.PlayerPageKt$$ExternalSyntheticLambda15
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return PlayerPageKt.PlayerPage$lambda$22$0(player, ((Long) obj2).longValue());
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                final Function1 function13 = (Function1) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 123291192, "CC(remember):PlayerPage.kt#9igjgp");
                zChangedInstance2 = composerStartRestartGroup.changedInstance(playbackManager);
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (!zChangedInstance2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue2 = new Function1() { // from class: com.stremio.tv.views.player.PlayerPageKt$$ExternalSyntheticLambda16
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return PlayerPageKt.PlayerPage$lambda$23$0(playbackManager, ((Float) obj2).floatValue());
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                function1 = (Function1) objRememberedValue2;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 123294001, "CC(remember):PlayerPage.kt#9igjgp");
                zChangedInstance3 = composerStartRestartGroup.changedInstance(immersionRememberImmersion) | composerStartRestartGroup.changedInstance(playbackManager);
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (!zChangedInstance3 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue3 = new Function0() { // from class: com.stremio.tv.views.player.PlayerPageKt$$ExternalSyntheticLambda17
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return PlayerPageKt.PlayerPage$lambda$24$0(immersionRememberImmersion, playbackManager);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                final Function0 function14 = (Function0) objRememberedValue3;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 123298049, "CC(remember):PlayerPage.kt#9igjgp");
                zChangedInstance4 = composerStartRestartGroup.changedInstance(playbackManager);
                objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                if (!zChangedInstance4 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue4 = new Function1() { // from class: com.stremio.tv.views.player.PlayerPageKt$$ExternalSyntheticLambda18
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return PlayerPageKt.PlayerPage$lambda$25$0(playbackManager, ((Double) obj2).doubleValue());
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                }
                Function1 function15 = (Function1) objRememberedValue4;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 123301568, "CC(remember):PlayerPage.kt#9igjgp");
                zChangedInstance5 = composerStartRestartGroup.changedInstance(playbackManager);
                objRememberedValue5 = composerStartRestartGroup.rememberedValue();
                if (!zChangedInstance5 || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue5 = new Function1() { // from class: com.stremio.tv.views.player.PlayerPageKt$$ExternalSyntheticLambda19
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return PlayerPageKt.PlayerPage$lambda$26$0(playbackManager, ((Double) obj2).doubleValue());
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                }
                Function1 function16 = (Function1) objRememberedValue5;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 123305122, "CC(remember):PlayerPage.kt#9igjgp");
                zChangedInstance6 = composerStartRestartGroup.changedInstance(playbackManager);
                objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                if (!zChangedInstance6 || objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue6 = new Function1() { // from class: com.stremio.tv.views.player.PlayerPageKt$$ExternalSyntheticLambda20
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return PlayerPageKt.PlayerPage$lambda$27$0(playbackManager, ((Double) obj2).doubleValue());
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
                }
                Function1 function17 = (Function1) objRememberedValue6;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 123308573, "CC(remember):PlayerPage.kt#9igjgp");
                zChangedInstance7 = composerStartRestartGroup.changedInstance(playbackManager);
                objRememberedValue7 = composerStartRestartGroup.rememberedValue();
                if (!zChangedInstance7 || objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue7 = new Function1() { // from class: com.stremio.tv.views.player.PlayerPageKt$$ExternalSyntheticLambda21
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return PlayerPageKt.PlayerPage$lambda$28$0(playbackManager, ((Double) obj2).doubleValue());
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue7);
                }
                Function1 function18 = (Function1) objRememberedValue7;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 123311836, "CC(remember):PlayerPage.kt#9igjgp");
                zChangedInstance8 = composerStartRestartGroup.changedInstance(playbackManager);
                objRememberedValue8 = composerStartRestartGroup.rememberedValue();
                if (!zChangedInstance8 || objRememberedValue8 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue8 = new Function1() { // from class: com.stremio.tv.views.player.PlayerPageKt$$ExternalSyntheticLambda11
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return PlayerPageKt.PlayerPage$lambda$29$0(playbackManager, (Track) obj2);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue8);
                }
                function2 = (Function1) objRememberedValue8;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 123315101, "CC(remember):PlayerPage.kt#9igjgp");
                zChangedInstance9 = composerStartRestartGroup.changedInstance(playbackManager);
                objRememberedValue9 = composerStartRestartGroup.rememberedValue();
                if (!zChangedInstance9 || objRememberedValue9 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue9 = new Function1() { // from class: com.stremio.tv.views.player.PlayerPageKt$$ExternalSyntheticLambda22
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return PlayerPageKt.PlayerPage$lambda$30$0(playbackManager, (Track) obj2);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue9);
                }
                function3 = (Function1) objRememberedValue9;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 123318351, "CC(remember):PlayerPage.kt#9igjgp");
                zChanged = composerStartRestartGroup.changed(function0Component2) | composerStartRestartGroup.changed(function0Component4) | composerStartRestartGroup.changed(function0Component6) | composerStartRestartGroup.changed(function0Component8) | composerStartRestartGroup.changed(function0Component10) | composerStartRestartGroup.changed(function0Component12) | composerStartRestartGroup.changed(function0Component14);
                objRememberedValue10 = composerStartRestartGroup.rememberedValue();
                if (!zChanged || objRememberedValue10 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue10 = new Function1() { // from class: com.stremio.tv.views.player.PlayerPageKt$$ExternalSyntheticLambda23
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return PlayerPageKt.PlayerPage$lambda$31$0(function0Component2, function0Component4, function0Component6, function0Component8, function0Component10, function0Component12, function0Component14, (Menu) obj2);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue10);
                }
                final Function1 function19 = (Function1) objRememberedValue10;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 123331129, "CC(remember):PlayerPage.kt#9igjgp");
                zChangedInstance10 = composerStartRestartGroup.changedInstance(playbackManager);
                objRememberedValue11 = composerStartRestartGroup.rememberedValue();
                if (!zChangedInstance10 || objRememberedValue11 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue11 = new Function1() { // from class: com.stremio.tv.views.player.PlayerPageKt$$ExternalSyntheticLambda24
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return PlayerPageKt.PlayerPage$lambda$32$0(playbackManager, (PlayerType) obj2);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue11);
                }
                function4 = (Function1) objRememberedValue11;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 123334229, "CC(remember):PlayerPage.kt#9igjgp");
                zChangedInstance11 = composerStartRestartGroup.changedInstance(player);
                objRememberedValue12 = composerStartRestartGroup.rememberedValue();
                if (!zChangedInstance11 || objRememberedValue12 == Composer.INSTANCE.getEmpty(

... [truncated: exceeds 60000 bytes, total 373369 bytes]