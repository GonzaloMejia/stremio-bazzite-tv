package com.stremio.tv.views.settings;

import androidx.activity.compose.BackHandlerKt;
import androidx.compose.animation.CrossfadeKt;
import androidx.compose.animation.SingleValueAnimationKt;
import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.EasingFunctionsKt;
import androidx.compose.animation.core.TweenSpec;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.FocusableKt;
import androidx.compose.foundation.interaction.FocusInteractionKt;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.lazy.LazyDslKt;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.foundation.lazy.LazyListScope;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.foundation.style.StylePropertiesKt;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
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
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.SaveableStateHolder;
import androidx.compose.runtime.saveable.SaveableStateHolderKt;
import androidx.compose.runtime.snapshots.SnapshotStateMap;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.focus.FocusChangedModifierKt;
import androidx.compose.ui.focus.FocusEnterExitScope;
import androidx.compose.ui.focus.FocusProperties;
import androidx.compose.ui.focus.FocusPropertiesKt;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.focus.FocusRequesterModifierKt;
import androidx.compose.ui.focus.FocusState;
import androidx.compose.ui.geometry.CornerRadius;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.input.pointer.PointerInputEventHandler;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.OnGloballyPositionedModifierKt;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.media3.extractor.ts.PsExtractor;
import androidx.navigation.NavController;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.stremio.common.composables.GradientsKt;
import com.stremio.common.extensions.ModifierExtKt;
import com.stremio.common.extensions.ProfileExtKt;
import com.stremio.common.translations.StringsKt;
import com.stremio.common.viewmodels.LoginViewModel;
import com.stremio.common.viewmodels.PreferencesState;
import com.stremio.common.viewmodels.PreferencesViewModel;
import com.stremio.common.viewmodels.SettingsViewModel;
import com.stremio.common.viewmodels.UserProfilesState;
import com.stremio.common.viewmodels.UserProfilesViewModel;
import com.stremio.core.models.LoadableSettings;
import com.stremio.core.models.StreamingServer;
import com.stremio.core.types.StreamPreset;
import com.stremio.core.types.profile.Auth;
import com.stremio.core.types.profile.Profile;
import com.stremio.translations.Strings;
import com.stremio.tv.Colors;
import com.stremio.tv.R;
import com.stremio.tv.ThemeKt;
import com.stremio.tv.views.settings.sections.AboutSectionKt;
import com.stremio.tv.views.settings.sections.AudioSectionKt;
import com.stremio.tv.views.settings.sections.GeneralSectionKt;
import com.stremio.tv.views.settings.sections.PlaybackSectionKt;
import com.stremio.tv.views.settings.sections.ServerSectionKt;
import com.stremio.tv.views.settings.sections.SubtitlesSectionKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.CoroutineScope;
import org.koin.compose.KoinApplicationKt;
import org.koin.viewmodel.CreationExtrasExtKt;
import org.koin.viewmodel.GetViewModelKt;

/* JADX INFO: compiled from: SettingsPage.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u008c\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\u001a/\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0007b\u0002\b\u0007b\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n¢\u0006\u0002\u0010\u0006\u001aZ\u0010\u000b\u001a\u00020\u00012\u0018\u0010\f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f0\u000e0\r2\u0006\u0010\u0010\u001a\u00020\u000f2\u0017\u0010\u0011\u001a\u0013\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00010\u0012¢\u0006\u0002\b\u0007H\u0003b\u0002\b\u0007b\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(\u0016¢\u0006\u0002\u0010\u0013\u001aY\u0010\u0017\u001a\u00020\u00012\u0006\u0010\u0018\u001a\u00020\u000f2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u00010\u00122\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00010 H\u0003b\u0002\b\u0007b\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n¢\u0006\u0002\u0010!¨\u0006\"²\u0006\f\u0010#\u001a\u0004\u0018\u00010$X\u008a\u0084\u0002²\u0006\f\u0010%\u001a\u0004\u0018\u00010&X\u008a\u0084\u0002²\u0006\n\u0010'\u001a\u00020(X\u008a\u0084\u0002²\u0006\f\u0010)\u001a\u0004\u0018\u00010*X\u008a\u0084\u0002²\u0006\n\u0010+\u001a\u00020,X\u008a\u0084\u0002²\u0006\u0010\u0010-\u001a\b\u0012\u0004\u0012\u00020.0\rX\u008a\u0084\u0002²\u0006\n\u0010\u0019\u001a\u00020\u000fX\u008a\u008e\u0002²\u0006\n\u0010/\u001a\u00020\u001aX\u008a\u008e\u0002²\u0006\n\u00100\u001a\u00020\u001aX\u008a\u008e\u0002²\u0006\n\u00101\u001a\u000202X\u008a\u0084\u0002²\u0006\n\u00103\u001a\u000204X\u008a\u0084\u0002²\u0006\n\u00105\u001a\u000204X\u008a\u0084\u0002²\u0006\n\u00106\u001a\u000204X\u008a\u0084\u0002²\u0006\n\u00107\u001a\u000204X\u008a\u0084\u0002²\u0006\n\u00108\u001a\u00020\u001aX\u008a\u0084\u0002²\u0006\n\u00109\u001a\u000202X\u008a\u0084\u0002"}, d2 = {"SettingsPage", "", "navController", "Landroidx/navigation/NavController;", "args", "Lcom/stremio/tv/views/settings/SettingsFragmentArgs;", "(Landroidx/navigation/NavController;Lcom/stremio/tv/views/settings/SettingsFragmentArgs;Landroidx/compose/runtime/Composer;I)V", "Landroidx/compose/runtime/Composable;", "Landroidx/compose/runtime/ComposableTarget;", "applier", "androidx.compose.ui.UiComposable", "Tabs", "tabs", "", "Lkotlin/Pair;", "", "default", FirebaseAnalytics.Param.CONTENT, "Lkotlin/Function1;", "(Ljava/util/List;Ljava/lang/String;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;I)V", "Landroidx/compose/runtime/ComposableInferredTarget;", "scheme", "[androidx.compose.ui.UiComposable[_]]", "Tab", "label", "selected", "", "focusRequester", "Landroidx/compose/ui/focus/FocusRequester;", "onPositioned", "Landroidx/compose/ui/geometry/Rect;", "onFocus", "Lkotlin/Function0;", "(Ljava/lang/String;ZLandroidx/compose/ui/focus/FocusRequester;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "Stremio:androidTV-com.stremio.one-1.11.2-40000002_release", "settings", "Lcom/stremio/core/types/profile/Profile$Settings;", "auth", "Lcom/stremio/core/types/profile/Auth;", "preferences", "Lcom/stremio/common/viewmodels/PreferencesState;", "streamingServer", "Lcom/stremio/core/models/StreamingServer;", "userProfiles", "Lcom/stremio/common/viewmodels/UserProfilesState;", "streamPresets", "Lcom/stremio/core/types/StreamPreset;", "tabsFocused", "contentFocused", TtmlNode.ATTR_TTS_BACKGROUND_COLOR, "Landroidx/compose/ui/graphics/Color;", "offsetX", "", "offsetY", "width", "height", "isFocused", "contentColor"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class SettingsPageKt {

    /* JADX INFO: compiled from: SettingsPage.kt */
    @Metadata(k = 3, mv = {2, 4, 0}, xi = 1328)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[LayoutDirection.values().length];
            try {
                iArr[LayoutDirection.Ltr.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[LayoutDirection.Rtl.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    static final Unit SettingsPage$lambda$15(NavController navController, SettingsFragmentArgs settingsFragmentArgs, int i, Composer composer, int i2) {
        SettingsPage(navController, settingsFragmentArgs, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit Tab$lambda$8(String str, boolean z, FocusRequester focusRequester, Function1 function1, Function0 function0, int i, Composer composer, int i2) {
        Tab(str, z, focusRequester, function1, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit Tabs$lambda$21(List list, String str, Function3 function3, int i, Composer composer, int i2) {
        Tabs(list, str, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

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
    public static final void SettingsPage(final NavController navController, final SettingsFragmentArgs args, Composer composer, final int i) {
        int i2;
        LoadableSettings settings;
        long jM5281constructorimpl;
        Intrinsics.checkNotNullParameter(navController, "navController");
        Intrinsics.checkNotNullParameter(args, "args");
        Composer composerStartRestartGroup = composer.startRestartGroup(-950526472);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(SettingsPage)N(navController,args)90@3991L7,91@4046L7,93@4102L15,94@4171L15,95@4228L15,96@4299L15,98@4363L16,99@4424L16,100@4491L16,101@4569L16,102@4638L16,103@4712L16,107@4788L430,118@5251L96,124@5387L58,128@5497L56,132@5580L121,136@5755L58,140@5895L47,144@6021L52,148@6079L281,157@6403L1538,157@6366L1575:SettingsPage.kt#hoovxv");
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changedInstance(navController) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= (i & 64) == 0 ? composerStartRestartGroup.changed(args) : composerStartRestartGroup.changedInstance(args) ? 32 : 16;
        }
        if (!composerStartRestartGroup.shouldExecute((i2 & 19) != 18, i2 & 1)) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-950526472, i2, -1, "com.stremio.tv.views.settings.SettingsPage (SettingsPage.kt:89)");
            }
            ProvidableCompositionLocal<Strings> localStrings = StringsKt.getLocalStrings();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = composerStartRestartGroup.consume(localStrings);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Strings strings = (Strings) objConsume;
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume2 = composerStartRestartGroup.consume(localLayoutDirection);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            LayoutDirection layoutDirection = (LayoutDirection) objConsume2;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -924953623, "CC(koinViewModel)N(qualifier,viewModelStoreOwner,key,extras,scope,parameters)48@1587L7,51@1782L18:ViewModel.kt#7bazx");
            ViewModelStoreOwner current = LocalViewModelStoreOwner.INSTANCE.getCurrent(composerStartRestartGroup, LocalViewModelStoreOwner.$stable);
            if (current == null) {
                throw new IllegalStateException("No ViewModelStoreOwner was provided via LocalViewModelStoreOwner".toString());
            }
            ViewModel viewModelResolveViewModel = GetViewModelKt.resolveViewModel(Reflection.getOrCreateKotlinClass(SettingsViewModel.class), current.getViewModelStore(), null, CreationExtrasExtKt.defaultExtras(current), null, KoinApplicationKt.currentKoinScope(composerStartRestartGroup, 0), null);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final SettingsViewModel settingsViewModel = (SettingsViewModel) viewModelResolveViewModel;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -924953623, "CC(koinViewModel)N(qualifier,viewModelStoreOwner,key,extras,scope,parameters)48@1587L7,51@1782L18:ViewModel.kt#7bazx");
            ViewModelStoreOwner current2 = LocalViewModelStoreOwner.INSTANCE.getCurrent(composerStartRestartGroup, LocalViewModelStoreOwner.$stable);
            if (current2 == null) {
                throw new IllegalStateException("No ViewModelStoreOwner was provided via LocalViewModelStoreOwner".toString());
            }
            ViewModel viewModelResolveViewModel2 = GetViewModelKt.resolveViewModel(Reflection.getOrCreateKotlinClass(PreferencesViewModel.class), current2.getViewModelStore(), null, CreationExtrasExtKt.defaultExtras(current2), null, KoinApplicationKt.currentKoinScope(composerStartRestartGroup, 0), null);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final PreferencesViewModel preferencesViewModel = (PreferencesViewModel) viewModelResolveViewModel2;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -924953623, "CC(koinViewModel)N(qualifier,viewModelStoreOwner,key,extras,scope,parameters)48@1587L7,51@1782L18:ViewModel.kt#7bazx");
            ViewModelStoreOwner current3 = LocalViewModelStoreOwner.INSTANCE.getCurrent(composerStartRestartGroup, LocalViewModelStoreOwner.$stable);
            if (current3 == null) {
                throw new IllegalStateException("No ViewModelStoreOwner was provided via LocalViewModelStoreOwner".toString());
            }
            ViewModel viewModelResolveViewModel3 = GetViewModelKt.resolveViewModel(Reflection.getOrCreateKotlinClass(LoginViewModel.class), current3.getViewModelStore(), null, CreationExtrasExtKt.defaultExtras(current3), null, KoinApplicationKt.currentKoinScope(composerStartRestartGroup, 0), null);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final LoginViewModel loginViewModel = (LoginViewModel) viewModelResolveViewModel3;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -924953623, "CC(koinViewModel)N(qualifier,viewModelStoreOwner,key,extras,scope,parameters)48@1587L7,51@1782L18:ViewModel.kt#7bazx");
            ViewModelStoreOwner current4 = LocalViewModelStoreOwner.INSTANCE.getCurrent(composerStartRestartGroup, LocalViewModelStoreOwner.$stable);
            if (current4 == null) {
                throw new IllegalStateException("No ViewModelStoreOwner was provided via LocalViewModelStoreOwner".toString());
            }
            ViewModel viewModelResolveViewModel4 = GetViewModelKt.resolveViewModel(Reflection.getOrCreateKotlinClass(UserProfilesViewModel.class), current4.getViewModelStore(), null, CreationExtrasExtKt.defaultExtras(current4), null, KoinApplicationKt.currentKoinScope(composerStartRestartGroup, 0), null);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final State stateCollectAsState = SnapshotStateKt.collectAsState(settingsViewModel.getSettings(), null, composerStartRestartGroup, 0, 1);
            final State stateCollectAsState2 = SnapshotStateKt.collectAsState(settingsViewModel.getAuthState(), null, composerStartRestartGroup, 0, 1);
            final State stateCollectAsState3 = SnapshotStateKt.collectAsState(preferencesViewModel.getState(), null, composerStartRestartGroup, 0, 1);
            final State stateCollectAsState4 = SnapshotStateKt.collectAsState(settingsViewModel.getStreamingServer(), null, composerStartRestartGroup, 0, 1);
            final State stateCollectAsState5 = SnapshotStateKt.collectAsState(((UserProfilesViewModel) viewModelResolveViewModel4).getState(), null, composerStartRestartGroup, 0, 1);
            final State stateCollectAsState6 = SnapshotStateKt.collectAsState(settingsViewModel.getStreamPresets(), null, composerStartRestartGroup, 0, 1);
            Auth authSettingsPage$lambda$1 = SettingsPage$lambda$1(stateCollectAsState2);
            final boolean zIsPremium = ProfileExtKt.isPremium(authSettingsPage$lambda$1 != null ? authSettingsPage$lambda$1.getUser() : null);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1372124198, "CC(remember):SettingsPage.kt#9igjgp");
            boolean zChanged = composerStartRestartGroup.changed(strings) | composerStartRestartGroup.changed(zIsPremium);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            String str = Tabs.GENERAL;
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                List listCreateListBuilder = CollectionsKt.createListBuilder();
                listCreateListBuilder.add(new Pair(Tabs.GENERAL, strings.getLabel_settings_nav_general()));
                listCreateListBuilder.add(new Pair(Tabs.SUBTITLES, strings.getLabel_subtitles()));
                listCreateListBuilder.add(new Pair("audio", strings.getLabel_audio()));
                listCreateListBuilder.add(new Pair(Tabs.PLAYBACK, strings.getLabel_playback()));
                listCreateListBuilder.add(new Pair(Tabs.SERVER, strings.getLabel_server()));
                listCreateListBuilder.add(new Pair(Tabs.ABOUT, strings.getLabel_about()));
                objRememberedValue = CollectionsKt.build(listCreateListBuilder);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            List list = (List) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1372138680, "CC(remember):SettingsPage.kt#9igjgp");
            boolean zChangedInstance = composerStartRestartGroup.changedInstance(loginViewModel) | composerStartRestartGroup.changedInstance(navController);
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = new Function0() { // from class: com.stremio.tv.views.settings.SettingsPageKt$$ExternalSyntheticLambda6
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return SettingsPageKt.SettingsPage$lambda$7$0(loginViewModel, navController);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            final Function0 function0 = (Function0) objRememberedValue2;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1372142994, "CC(remember):SettingsPage.kt#9igjgp");
            boolean zChangedInstance2 = composerStartRestartGroup.changedInstance(navController);
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue3 = new Function0() { // from class: com.stremio.tv.views.settings.SettingsPageKt$$ExternalSyntheticLambda7
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return SettingsPageKt.SettingsPage$lambda$8$0(navController);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            final Function0 function1 = (Function0) objRememberedValue3;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1372146512, "CC(remember):SettingsPage.kt#9igjgp");
            boolean zChangedInstance3 = composerStartRestartGroup.changedInstance(settingsViewModel);
            Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance3 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue4 = new Function1() { // from class: com.stremio.tv.views.settings.SettingsPageKt$$ExternalSyntheticLambda8
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return SettingsPageKt.SettingsPage$lambda$9$0(settingsViewModel, (String) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            }
            final Function1 function2 = (Function1) objRememberedValue4;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            StreamingServer streamingServerSettingsPage$lambda$3 = SettingsPage$lambda$3(stateCollectAsState4);
            LoadableSettings settings2 = streamingServerSettingsPage$lambda$3 != null ? streamingServerSettingsPage$lambda$3.getSettings() : null;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1372149233, "CC(remember):SettingsPage.kt#9igjgp");
            boolean zChanged2 = composerStartRestartGroup.changed(settings2);
            Object objRememberedValue5 = composerStartRestartGroup.rememberedValue();
            if (zChanged2 || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                StreamingServer streamingServerSettingsPage$lambda$4 = SettingsPage$lambda$3(stateCollectAsState4);
                objRememberedValue5 = settingsViewModel.getTorrentProfile((streamingServerSettingsPage$lambda$4 == null || (settings = streamingServerSettingsPage$lambda$4.getSettings()) == null) ? null : settings.getReady());
                composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
            }
            final String str2 = (String) objRememberedValue5;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1372154770, "CC(remember):SettingsPage.kt#9igjgp");
            boolean zChangedInstance4 = composerStartRestartGroup.changedInstance(settingsViewModel);
            Object objRememberedValue6 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance4 || objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue6 = new Function1() { // from class: com.stremio.tv.views.settings.SettingsPageKt$$ExternalSyntheticLambda9
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return SettingsPageKt.SettingsPage$lambda$11$0(settingsViewModel, (String) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
            }
            final Function1 function3 = (Function1) objRememberedValue6;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1372159239, "CC(remember):SettingsPage.kt#9igjgp");
            boolean zChangedInstance5 = composerStartRestartGroup.changedInstance(preferencesViewModel);
            Object objRememberedValue7 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance5 || objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue7 = new Function1() { // from class: com.stremio.tv.views.settings.SettingsPageKt$$ExternalSyntheticLambda10
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return SettingsPageKt.SettingsPage$lambda$12$0(preferencesViewModel, (Function1) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue7);
            }
            final Function1 function4 = (Function1) objRememberedValue7;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1372163276, "CC(remember):SettingsPage.kt#9igjgp");
            boolean zChangedInstance6 = composerStartRestartGroup.changedInstance(settingsViewModel);
            Object objRememberedValue8 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance6 || objRememberedValue8 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue8 = new Function1() { // from class: com.stremio.tv.views.settings.SettingsPageKt$$ExternalSyntheticLambda12
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return SettingsPageKt.SettingsPage$lambda$13$0(settingsViewModel, (Function1) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue8);
            }
            final Function1 function5 = (Function1) objRememberedValue8;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            long jM5567copywmQWz5c$default = Color.m5567copywmQWz5c$default(Colors.INSTANCE.m11513getPrimaryAccent0d7_KjU(), 0.7f, 0.0f, 0.0f, 0.0f, 14, null);
            int i3 = WhenMappings.$EnumSwitchMapping$0[layoutDirection.ordinal()];
            if (i3 == 1) {
                jM5281constructorimpl = Offset.m5281constructorimpl((((long) Float.floatToRawIntBits(Float.POSITIVE_INFINITY)) << 32) | (((long) Float.floatToRawIntBits(100.0f)) & 4294967295L));
            } else {
                if (i3 != 2) {
                    throw new NoWhenBranchMatchedException();
                }
                jM5281constructorimpl = Offset.m5281constructorimpl((((long) Float.floatToRawIntBits(0.0f)) & 4294967295L) | (((long) Float.floatToRawIntBits(100.0f)) << 32));
            }
            GradientsKt.m11459RadialGradient2VII3NU(jM5567copywmQWz5c$default, jM5281constructorimpl, 0.5f, composerStartRestartGroup, RendererCapabilities.DECODER_SUPPORT_MASK);
            String tab = args.getTab();
            if (tab != null) {
                str = tab;
            }
            Tabs(list, str, ComposableLambdaKt.rememberComposableLambda(-327647948, true, new Function3() { // from class: com.stremio.tv.views.settings.SettingsPageKt$$ExternalSyntheticLambda13
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return SettingsPageKt.SettingsPage$lambda$14(zIsPremium, function0, function1, function2, function4, function5, str2, function3, stateCollectAsState2, stateCollectAsState, stateCollectAsState3, stateCollectAsState5, stateCollectAsState6, stateCollectAsState4, (String) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, composerStartRestartGroup, 54), composerStartRestartGroup, RendererCapabilities.DECODER_SUPPORT_MASK);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.stremio.tv.views.settings.SettingsPageKt$$ExternalSyntheticLambda14
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SettingsPageKt.SettingsPage$lambda$15(navController, args, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SettingsPage$lambda$7$0(LoginViewModel loginViewModel, final NavController navController) {
        loginViewModel.logout(new Function0() { // from class: com.stremio.tv.views.settings.SettingsPageKt$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SettingsPageKt.SettingsPage$lambda$7$0$0(navController);
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SettingsPage$lambda$7$0$0(NavController navController) {
        navController.navigate(R.id.login);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SettingsPage$lambda$8$0(NavController navController) {
        navController.navigate(R.id.user_profiles);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SettingsPage$lambda$9$0(SettingsViewModel settingsViewModel, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        settingsViewModel.selectStreamPreset(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SettingsPage$lambda$11$0(SettingsViewModel settingsViewModel, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        settingsViewModel.updateTorrentProfile(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SettingsPage$lambda$12$0(PreferencesViewModel preferencesViewModel, Function1 it) {
        Intrinsics.checkNotNullParameter(it, "it");
        preferencesViewModel.update(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SettingsPage$lambda$13$0(SettingsViewModel settingsViewModel, Function1 it) {
        Intrinsics.checkNotNullParameter(it, "it");
        settingsViewModel.updateSettings(it);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:46:0x015e  */
    static final Unit SettingsPage$lambda$14(boolean z, Function0 function0, Function0 function1, Function1 function2, Function1 function3, Function1 function4, String str, Function1 function5, State state, State state2, State state3, State state4, State state5, State state6, String tab, Composer composer, int i) {
        int i2;
        Intrinsics.checkNotNullParameter(tab, "tab");
        ComposerKt.sourceInformation(composer, "CN(tab):SettingsPage.kt#hoovxv");
        if ((i & 6) == 0) {
            i2 = i | (composer.changed(tab) ? 4 : 2);
        } else {
            i2 = i;
        }
        if (composer.shouldExecute((i2 & 19) != 18, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-327647948, i2, -1, "com.stremio.tv.views.settings.SettingsPage.<anonymous> (SettingsPage.kt:158)");
            }
            switch (tab) {
                case "server":
                    composer.startReplaceGroup(1501746248);
                    ComposerKt.sourceInformation(composer, "195@7522L262");
                    ServerSectionKt.ServerSection(SettingsPage$lambda$3(state6), str, SettingsPage$lambda$2(state3), function5, function3, function4, composer, PreferencesState.$stable << 6);
                    composer.endReplaceGroup();
                    break;
                case "general":
                    composer.startReplaceGroup(1500716521);
                    ComposerKt.sourceInformation(composer, "160@6479L421");
                    GeneralSectionKt.GeneralSection(SettingsPage$lambda$1(state), SettingsPage$lambda$0(state2), SettingsPage$lambda$2(state3), SettingsPage$lambda$4(state4).getProfiles(), SettingsPage$lambda$5(state5), z, function0, function1, function2, function3, function4, composer, PreferencesState.$stable << 6, 0);
                    composer.endReplaceGroup();
                    break;
                case "about":
                    composer.startReplaceGroup(1502058666);
                    ComposerKt.sourceInformation(composer, "205@7843L68");
                    AboutSectionKt.AboutSection(SettingsPage$lambda$3(state6), composer, 0);
                    composer.endReplaceGroup();
                    break;
                case "audio":
                    composer.startReplaceGroup(1501347371);
                    ComposerKt.sourceInformation(composer, "181@7125L99");
                    AudioSectionKt.AudioSection(SettingsPage$lambda$0(state2), function4, composer, 0);
                    composer.endReplaceGroup();
                    break;
                case "subtitles":
                    composer.startReplaceGroup(1501186791);
                    ComposerKt.sourceInformation(composer, "175@6963L103");
                    SubtitlesSectionKt.SubtitlesSection(SettingsPage$lambda$0(state2), function4, composer, 0);
                    composer.endReplaceGroup();
                    break;
                case "playback":
                    composer.startReplaceGroup(1501509470);
                    ComposerKt.sourceInformation(composer, "187@7286L176");
                    PlaybackSectionKt.PlaybackSection(SettingsPage$lambda$0(state2), SettingsPage$lambda$2(state3), function3, function4, composer, PreferencesState.$stable << 3);
                    composer.endReplaceGroup();
                    break;
                default:
                    composer.startReplaceGroup(1502164686);
                    composer.endReplaceGroup();
                    break;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    private static final void Tabs(final List<Pair<String, String>> list, final String str, Function3<? super String, ? super Composer, ? super Integer, Unit> function3, Composer composer, final int i) {
        int i2;
        final Function3<? super String, ? super Composer, ? super Integer, Unit> function4;
        long jM5567copywmQWz5c$default;
        int i3;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1236093322);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(Tabs)N(tabs,default,content)219@8118L27,219@8101L44,220@8176L29,221@8229L34,222@8290L34,224@8351L29,225@8411L29,227@8461L46,230@8578L248,239@8927L118,243@9065L117,247@9200L119,251@9338L120,256@9500L29,260@9579L45,260@9535L89,268@9742L111,264@9630L2329:SettingsPage.kt#hoovxv");
        if ((i & 6) == 0) {
            i2 = ((i & 8) == 0 ? composerStartRestartGroup.changed(list) : composerStartRestartGroup.changedInstance(list) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changed(str) ? 32 : 16;
        }
        if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function3) ? 256 : 128;
        }
        if (!composerStartRestartGroup.shouldExecute((i2 & 147) != 146, i2 & 1)) {
            function4 = function3;
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1236093322, i2, -1, "com.stremio.tv.views.settings.Tabs (SettingsPage.kt:218)");
            }
            Object[] objArr = new Object[0];
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1820263473, "CC(remember):SettingsPage.kt#9igjgp");
            boolean z = (i2 & StylePropertiesKt.TextDirectionMask) == 32;
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (z || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function0() { // from class: com.stremio.tv.views.settings.SettingsPageKt$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return SettingsPageKt.Tabs$lambda$0$0(str);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final MutableState mutableState = (MutableState) RememberSaveableKt.rememberSaveable(objArr, (Function0) objRememberedValue, composerStartRestartGroup, 0);
            final SaveableStateHolder saveableStateHolderRememberSaveableStateHolder = SaveableStateHolderKt.rememberSaveableStateHolder(composerStartRestartGroup, 0);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1820267032, "CC(remember):SettingsPage.kt#9igjgp");
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            final MutableState mutableState2 = (MutableState) objRememberedValue2;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1820268984, "CC(remember):SettingsPage.kt#9igjgp");
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            final MutableState mutableState3 = (MutableState) objRememberedValue3;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1820270931, "CC(remember):SettingsPage.kt#9igjgp");
            Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue4 = new FocusRequester();
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            }
            final FocusRequester focusRequester = (FocusRequester) objRememberedValue4;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1820272851, "CC(remember):SettingsPage.kt#9igjgp");
            Object objRememberedValue5 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue5 = new FocusRequester();
                composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
            }
            final FocusRequester focusRequester2 = (FocusRequester) objRememberedValue5;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1820274468, "CC(remember):SettingsPage.kt#9igjgp");
            Object objRememberedValue6 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue6 = SnapshotStateKt.mutableStateMapOf();
                composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
            }
            final SnapshotStateMap snapshotStateMap = (SnapshotStateMap) objRememberedValue6;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final Rect rect = (Rect) snapshotStateMap.get(Tabs$lambda$1(mutableState));
            boolean zTabs$lambda$4 = Tabs$lambda$4(mutableState2);
            if (zTabs$lambda$4) {
                jM5567copywmQWz5c$default = Colors.INSTANCE.m11517getSecondaryBackground0d7_KjU();
            } else {
                if (zTabs$lambda$4) {
                    throw new NoWhenBranchMatchedException();
                }
                jM5567copywmQWz5c$default = Color.m5567copywmQWz5c$default(Colors.INSTANCE.m11517getSecondaryBackground0d7_KjU(), 0.2f, 0.0f, 0.0f, 0.0f, 14, null);
            }
            final State<Color> stateM205animateColorAsStateeuL9pac = SingleValueAnimationKt.m205animateColorAsStateeuL9pac(jM5567copywmQWz5c$default, AnimationSpecKt.tween$default(100, 0, EasingFunctionsKt.getEaseOut(), 2, null), null, null, composerStartRestartGroup, 0, 12);
            TweenSpec tweenSpecTween$default = AnimationSpecKt.tween$default(100, 0, EasingFunctionsKt.getEaseOut(), 2, null);
            final State<Float> stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(rect != null ? rect.getLeft() : 0.0f, tweenSpecTween$default, 0.0f, null, null, composerStartRestartGroup, 0, 28);
            final State<Float> stateAnimateFloatAsState2 = AnimateAsStateKt.animateFloatAsState(rect != null ? rect.getTop() : 0.0f, tweenSpecTween$default, 0.0f, null, null, composerStartRestartGroup, 0, 28);
            final State<Float> stateAnimateFloatAsState3 = AnimateAsStateKt.animateFloatAsState(rect != null ? rect.getRight() - rect.getLeft() : 0.0f, tweenSpecTween$default, 0.0f, null, null, composerStartRestartGroup, 0, 28);
            final State<Float> stateAnimateFloatAsState4 = AnimateAsStateKt.animateFloatAsState(rect != null ? rect.getBottom() - rect.getTop() : 0.0f, tweenSpecTween$default, 0.0f, null, null, composerStartRestartGroup, 0, 28);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1820307699, "CC(remember):SettingsPage.kt#9igjgp");
            boolean zChanged = composerStartRestartGroup.changed(mutableState);
            Object objRememberedValue7 = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue7 = new Function1() { // from class: com.stremio.tv.views.settings.SettingsPageKt$$ExternalSyntheticLambda18
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return SettingsPageKt.Tabs$lambda$17$0(mutableState, (String) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue7);
            }
            final Function1 function1 = (Function1) objRememberedValue7;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            boolean z2 = !Tabs$lambda$4(mutableState2) && Tabs$lambda$7(mutableState3);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1820310243, "CC(remember):SettingsPage.kt#9igjgp");
            Object objRememberedValue8 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue8 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue8 = new Function0() { // from class: com.stremio.tv.views.settings.SettingsPageKt$$ExternalSyntheticLambda19
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return SettingsPageKt.Tabs$lambda$18$0(focusRequester);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue8);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            BackHandlerKt.BackHandler(z2, (Function0) objRememberedValue8, composerStartRestartGroup, 48, 0);
            Modifier modifierM1121paddingqDBjuR0$default = PaddingKt.m1121paddingqDBjuR0$default(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, 0.0f, Dp.m8482constructorimpl(85.0f), 0.0f, 11, null);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1820315525, "CC(remember):SettingsPage.kt#9igjgp");
            Object objRememberedValue9 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue9 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue9 = new Function1() { // from class: com.stremio.tv.views.settings.SettingsPageKt$$ExternalSyntheticLambda20
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return SettingsPageKt.Tabs$lambda$19$0(focusRequester, (FocusProperties) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue9);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Modifier modifierFocusProperties = FocusPropertiesKt.focusProperties(modifierM1121paddingqDBjuR0$default, (Function1) objRememberedValue9);
            Alignment topCenter = Alignment.INSTANCE.getTopCenter();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(topCenter, false);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierFocusProperties);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i4 = i2;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16216L9:Composables.kt#9igjgp");
            if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composerM4659constructorimpl = Updater.m4659constructorimpl(composerStartRestartGroup);
            Updater.m4667setimpl(composerM4659constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4667setimpl(composerM4659constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4667setimpl(composerM4659constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4665reconcileimpl(composerM4659constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4667setimpl(composerM4659constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1220246175, "C282@10219L397,293@10679L65,296@10778L127,303@11042L462,275@9919L1585,321@11657L68,326@11831L122,317@11514L439:SettingsPage.kt#hoovxv");
            Modifier modifierM1119paddingVpY3zN4$default = PaddingKt.m1119paddingVpY3zN4$default(BackgroundKt.m342backgroundbw27NRU$default(ClipKt.clip(SizeKt.m1170height3ABfNKs(PaddingKt.m1119paddingVpY3zN4$default(Modifier.INSTANCE, 0.0f, Dp.m8482constructorimpl(10.0f), 1, null), Dp.m8482constructorimpl(40.0f)), RoundedCornerShapeKt.m1450RoundedCornerShape0680j_4(Dp.m8482constructorimpl(40.0f))), Color.m5567copywmQWz5c$default(Colors.INSTANCE.m11517getSecondaryBackground0d7_KjU(), 0.1f, 0.0f, 0.0f, 0.0f, 14, null), null, 2, null), Dp.m8482constructorimpl(5.0f), 0.0f, 2, null);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2038855165, "CC(remember):SettingsPage.kt#9igjgp");
            boolean zChanged2 = composerStartRestartGroup.changed(rect) | composerStartRestartGroup.changed(stateM205animateColorAsStateeuL9pac) | composerStartRestartGroup.changed(stateAnimateFloatAsState) | composerStartRestartGroup.changed(stateAnimateFloatAsState2) | composerStartRestartGroup.changed(stateAnimateFloatAsState3) | composerStartRestartGroup.changed(stateAnimateFloatAsState4);
            Object objRememberedValue10 = composerStartRestartGroup.rememberedValue();
            if (zChanged2 || objRememberedValue10 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue10 = new Function1() { // from class: com.stremio.tv.views.settings.SettingsPageKt$$ExternalSyntheticLambda21
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return SettingsPageKt.Tabs$lambda$20$0$0(rect, stateM205animateColorAsStateeuL9pac, stateAnimateFloatAsState, stateAnimateFloatAsState2, stateAnimateFloatAsState3, stateAnimateFloatAsState4, (DrawScope) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue10);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Modifier modifierFocusGroup = FocusableKt.focusGroup(DrawModifierKt.drawBehind(modifierM1119paddingVpY3zN4$default, (Function1) objRememberedValue10));
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2038869553, "CC(remember):SettingsPage.kt#9igjgp");
            Object objRememberedValue11 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue11 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue11 = new Function1() { // from class: com.stremio.tv.views.settings.SettingsPageKt$$ExternalSyntheticLambda22
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return SettingsPageKt.Tabs$lambda$20$1$0(mutableState2, (FocusState) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue11);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Modifier modifierOnFocusChanged = FocusChangedModifierKt.onFocusChanged(modifierFocusGroup, (Function1) objRememberedValue11);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2038872783, "CC(remember):SettingsPage.kt#9igjgp");
            Object objRememberedValue12 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue12 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue12 = new Function1() { // from class: com.stremio.tv.views.settings.SettingsPageKt$$ExternalSyntheticLambda23
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return SettingsPageKt.Tabs$lambda$20$2$0(focusRequester, (FocusProperties) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue12);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Modifier modifierFocusProperties2 = FocusPropertiesKt.focusProperties(modifierOnFocusChanged, (Function1) objRememberedValue12);
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            Arrangement.HorizontalOrVertical horizontalOrVerticalM809spacedBy0680j_4 = Arrangement.INSTANCE.m809spacedBy0680j_4(Dp.m8482constructorimpl(10.0f));
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2038881566, "CC(remember):SettingsPage.kt#9igjgp");
            boolean zChanged3 = composerStartRestartGroup.changed(mutableState) | ((i4 & 14) == 4 || ((i4 & 8) != 0 && composerStartRestartGroup.changedInstance(list))) | composerStartRestartGroup.changed(function1);
            Object objRememberedValue13 = composerStartRestartGroup.rememberedValue();
            if (zChanged3 || objRememberedValue13 == Composer.INSTANCE.getEmpty()) {
                i3 = 2;
                Function1 function2 = new Function1() { // from class: com.stremio.tv.views.settings.SettingsPageKt$$ExternalSyntheticLambda24
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return SettingsPageKt.Tabs$lambda$20$3$0(list, focusRequester, focusRequester2, function1, mutableState, snapshotStateMap, (LazyListScope) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(function2);
                objRememberedValue13 = function2;
            } else {
                i3 = 2;
            }
            Function1 function5 = (Function1) objRememberedValue13;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            int i5 = i3;
            LazyDslKt.LazyRow(modifierFocusProperties2, null, null, false, horizontalOrVerticalM809spacedBy0680j_4, centerVertically, null, false, null, function5, composerStartRestartGroup, 221184, 462);
            composerStartRestartGroup = composerStartRestartGroup;
            Modifier modifierFocusGroup2 = FocusableKt.focusGroup(PaddingKt.m1121paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, Dp.m8482constructorimpl(50.0f), 0.0f, 0.0f, 13, null));
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 20

... [truncated: exceeds 60000 bytes, total 93639 bytes]