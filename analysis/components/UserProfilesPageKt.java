package com.stremio.tv.views.userprofiles;

import androidx.activity.compose.BackHandlerKt;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.BorderKt;
import androidx.compose.foundation.FocusableKt;
import androidx.compose.foundation.interaction.FocusInteractionKt;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
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
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.focus.FocusEnterExitScope;
import androidx.compose.ui.focus.FocusProperties;
import androidx.compose.ui.focus.FocusPropertiesKt;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.focus.FocusRequesterModifierKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.input.key.Key;
import androidx.compose.ui.input.key.KeyEvent;
import androidx.compose.ui.input.key.KeyEventType;
import androidx.compose.ui.input.key.KeyEvent_androidKt;
import androidx.compose.ui.input.key.KeyInputModifierKt;
import androidx.compose.ui.input.pointer.PointerInputEventHandler;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.exifinterface.media.ExifInterface;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.exoplayer.rtsp.SessionDescription;
import androidx.media3.extractor.ts.PsExtractor;
import androidx.navigation.NavController;
import com.google.android.gms.common.Scopes;
import com.stremio.common.composables.AvatarKt;
import com.stremio.common.extensions.DownloadInfoExtKt;
import com.stremio.common.extensions.ModifierExtKt;
import com.stremio.common.translations.StringsKt;
import com.stremio.common.viewmodels.LoginViewModel;
import com.stremio.common.viewmodels.UserProfilesState;
import com.stremio.common.viewmodels.UserProfilesViewModel;
import com.stremio.core.models.UserProfile;
import com.stremio.icons.Icons;
import com.stremio.translations.Strings;
import com.stremio.tv.Colors;
import com.stremio.tv.R;
import com.stremio.tv.Shapes;
import com.stremio.tv.Sizes;
import com.stremio.tv.ThemeKt;
import com.stremio.tv.composables.ButtonKt;
import com.stremio.tv.composables.RemoteImageKt;
import io.ktor.sse.ServerSentEventKt;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: UserProfilesPage.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000X\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a7\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007b\u0002\b\tb\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f¢\u0006\u0002\u0010\b\u001aG\u0010\r\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00010\u0015H\u0001b\u0002\b\tb\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f¢\u0006\u0002\u0010\u0016\u001aS\u0010\u0017\u001a\u00020\u00012\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u00192\u0012\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00010\u001c2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00010\u0015H\u0003b\u0002\b\tb\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f¢\u0006\u0002\u0010\u001e\u001a?\u0010\u001f\u001a\u00020\u00012\u0006\u0010 \u001a\u00020\u00192\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00010\u0015H\u0003b\u0002\b\tb\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f¢\u0006\u0002\u0010!¨\u0006\"²\u0006\n\u0010#\u001a\u00020$X\u008a\u0084\u0002²\u0006\f\u0010%\u001a\u0004\u0018\u00010\u0019X\u008a\u0084\u0002²\u0006\f\u0010&\u001a\u0004\u0018\u00010\u0011X\u008a\u008e\u0002²\u0006\n\u0010'\u001a\u00020\u0013X\u008a\u0084\u0002²\u0006\n\u0010(\u001a\u00020\u0019X\u008a\u008e\u0002²\u0006\n\u0010'\u001a\u00020\u0013X\u008a\u0084\u0002"}, d2 = {"UserProfilesPage", "", "navController", "Landroidx/navigation/NavController;", "userProfilesViewModel", "Lcom/stremio/common/viewmodels/UserProfilesViewModel;", "loginViewModel", "Lcom/stremio/common/viewmodels/LoginViewModel;", "(Landroidx/navigation/NavController;Lcom/stremio/common/viewmodels/UserProfilesViewModel;Lcom/stremio/common/viewmodels/LoginViewModel;Landroidx/compose/runtime/Composer;I)V", "Landroidx/compose/runtime/Composable;", "Landroidx/compose/runtime/ComposableTarget;", "applier", "androidx.compose.ui.UiComposable", "ProfileItem", "modifier", "Landroidx/compose/ui/Modifier;", Scopes.PROFILE, "Lcom/stremio/core/models/UserProfile;", "compact", "", "onClick", "Lkotlin/Function0;", "(Landroidx/compose/ui/Modifier;Lcom/stremio/core/models/UserProfile;ZLkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;II)V", "PinDialog", "profileName", "", DownloadInfoExtKt.DOWNLOAD_STATUS_ERROR, "onConfirm", "Lkotlin/Function1;", "onDismiss", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "PinKey", "label", "(Ljava/lang/String;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;II)V", "Stremio:androidTV-com.stremio.one-1.11.2-40000002_release", "userProfiles", "Lcom/stremio/common/viewmodels/UserProfilesState;", "profileSwitchError", "pinProfile", "isFocused", "pin"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class UserProfilesPageKt {
    static final Unit PinDialog$lambda$8(String str, String str2, Function1 function1, Function0 function0, int i, Composer composer, int i2) {
        PinDialog(str, str2, function1, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit PinKey$lambda$5(String str, Modifier modifier, Function0 function0, int i, int i2, Composer composer, int i3) {
        PinKey(str, modifier, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit ProfileItem$lambda$5(Modifier modifier, UserProfile userProfile, boolean z, Function0 function0, int i, int i2, Composer composer, int i3) {
        ProfileItem(modifier, userProfile, z, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit UserProfilesPage$lambda$9(NavController navController, UserProfilesViewModel userProfilesViewModel, LoginViewModel loginViewModel, int i, Composer composer, int i2) {
        UserProfilesPage(navController, userProfilesViewModel, loginViewModel, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static final void UserProfilesPage(final NavController navController, final UserProfilesViewModel userProfilesViewModel, final LoginViewModel loginViewModel, Composer composer, final int i) {
        Intrinsics.checkNotNullParameter(navController, "navController");
        Intrinsics.checkNotNullParameter(userProfilesViewModel, "userProfilesViewModel");
        Intrinsics.checkNotNullParameter(loginViewModel, "loginViewModel");
        Composer composerStartRestartGroup = composer.startRestartGroup(-701160310);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(UserProfilesPage)N(navController,userProfilesViewModel,loginViewModel)68@2903L7,69@2963L16,70@3051L16,71@3090L47,73@3165L107,80@3299L133,80@3278L154,87@3482L449,101@3937L2199:UserProfilesPage.kt#v7e3id");
        int i2 = (i & 6) == 0 ? (composerStartRestartGroup.changedInstance(navController) ? 4 : 2) | i : i;
        if ((i & 48) == 0) {
            i2 |= (i & 64) == 0 ? composerStartRestartGroup.changed(userProfilesViewModel) : composerStartRestartGroup.changedInstance(userProfilesViewModel) ? 32 : 16;
        }
        if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i2 |= (i & 512) == 0 ? composerStartRestartGroup.changed(loginViewModel) : composerStartRestartGroup.changedInstance(loginViewModel) ? 256 : 128;
        }
        if (!composerStartRestartGroup.shouldExecute((i2 & 147) != 146, i2 & 1)) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-701160310, i2, -1, "com.stremio.tv.views.userprofiles.UserProfilesPage (UserProfilesPage.kt:67)");
            }
            ProvidableCompositionLocal<Strings> localStrings = StringsKt.getLocalStrings();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = composerStartRestartGroup.consume(localStrings);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Strings strings = (Strings) objConsume;
            State stateCollectAsState = SnapshotStateKt.collectAsState(userProfilesViewModel.getState(), null, composerStartRestartGroup, 0, 1);
            State stateCollectAsState2 = SnapshotStateKt.collectAsState(userProfilesViewModel.getProfileSwitchError(), null, composerStartRestartGroup, 0, 1);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 494819257, "CC(remember):UserProfilesPage.kt#9igjgp");
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            final MutableState mutableState = (MutableState) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 494821717, "CC(remember):UserProfilesPage.kt#9igjgp");
            boolean zChangedInstance = composerStartRestartGroup.changedInstance(navController);
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = new Function0() { // from class: com.stremio.tv.views.userprofiles.UserProfilesPageKt$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return UserProfilesPageKt.UserProfilesPage$lambda$5$0(navController);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            final Function0 function0 = (Function0) objRememberedValue2;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Unit unit = Unit.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 494826031, "CC(remember):UserProfilesPage.kt#9igjgp");
            int i3 = i2 & StylePropertiesKt.TextDirectionMask;
            boolean zChanged = (i3 == 32 || ((i2 & 64) != 0 && composerStartRestartGroup.changedInstance(userProfilesViewModel))) | composerStartRestartGroup.changed(function0);
            UserProfilesPageKt$UserProfilesPage$1$1 userProfilesPageKt$UserProfilesPage$1$1RememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged || userProfilesPageKt$UserProfilesPage$1$1RememberedValue == Composer.INSTANCE.getEmpty()) {
                userProfilesPageKt$UserProfilesPage$1$1RememberedValue = new UserProfilesPageKt$UserProfilesPage$1$1(userProfilesViewModel, function0, mutableState, null);
                composerStartRestartGroup.updateRememberedValue(userProfilesPageKt$UserProfilesPage$1$1RememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            EffectsKt.LaunchedEffect(unit, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) userProfilesPageKt$UserProfilesPage$1$1RememberedValue, composerStartRestartGroup, 6);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 494832203, "CC(remember):UserProfilesPage.kt#9igjgp");
            boolean zChanged2 = composerStartRestartGroup.changed(function0) | (i3 == 32 || ((i2 & 64) != 0 && composerStartRestartGroup.changedInstance(userProfilesViewModel)));
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (zChanged2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue3 = new Function1() { // from class: com.stremio.tv.views.userprofiles.UserProfilesPageKt$$ExternalSyntheticLambda6
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return UserProfilesPageKt.UserProfilesPage$lambda$7$0(function0, userProfilesViewModel, mutableState, (UserProfile) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            Function1 function1 = (Function1) objRememberedValue3;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Modifier modifierM342backgroundbw27NRU$default = BackgroundKt.m342backgroundbw27NRU$default(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), Colors.INSTANCE.m11514getPrimaryBackground0d7_KjU(), null, 2, null);
            Alignment center = Alignment.INSTANCE.getCenter();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM342backgroundbw27NRU$default);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
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
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -2010985106, "C107@4109L1564:UserProfilesPage.kt#v7e3id");
            Alignment.Horizontal centerHorizontally = Alignment.INSTANCE.getCenterHorizontally();
            Arrangement.Vertical verticalM811spacedByD5KLDUw = Arrangement.INSTANCE.m811spacedByD5KLDUw(Dp.m8482constructorimpl(48.0f), Alignment.INSTANCE.getCenterVertically());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            Modifier.Companion companion = Modifier.INSTANCE;
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(verticalM811spacedByD5KLDUw, centerHorizontally, composerStartRestartGroup, 54);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
            CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion);
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16216L9:Composables.kt#9igjgp");
            if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor2);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composerM4659constructorimpl2 = Updater.m4659constructorimpl(composerStartRestartGroup);
            Updater.m4667setimpl(composerM4659constructorimpl2, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4667setimpl(composerM4659constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4667setimpl(composerM4659constructorimpl2, Integer.valueOf(iHashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4665reconcileimpl(composerM4659constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4667setimpl(composerM4659constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -35057530, "C113@4399L16,111@4296L253,120@4621L578,139@5373L275,136@5213L450:UserProfilesPage.kt#v7e3id");
            int i4 = i2;
            Function1 function2 = function1;
            String str = "CC(remember):UserProfilesPage.kt#9igjgp";
            TextKt.m3353TextNvy7gAk(strings.getLabel_user_profiles_switch_profile_title(), null, Colors.INSTANCE.m11515getPrimaryForeground0d7_KjU(), null, 0L, null, FontWeight.INSTANCE.getBold(), null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m7918copyp1EtxEg$default(ThemeKt.labelTextStyle(composerStartRestartGroup, 0), 0L, TextUnitKt.getSp(32), null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777213, null), composerStartRestartGroup, 1572864, 0, 131002);
            boolean z = UserProfilesPage$lambda$0(stateCollectAsState).getProfiles().size() > 4;
            Arrangement.Horizontal horizontalM810spacedByD5KLDUw = Arrangement.INSTANCE.m810spacedByD5KLDUw(Dp.m8482constructorimpl(z ? 16.0f : 32.0f), Alignment.INSTANCE.getCenterHorizontally());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
            Modifier.Companion companion2 = Modifier.INSTANCE;
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(horizontalM810spacedByD5KLDUw, Alignment.INSTANCE.getTop(), composerStartRestartGroup, 0);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode3 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
            CompositionLocalMap currentCompositionLocalMap3 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion2);
            Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16216L9:Composables.kt#9igjgp");
            if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor3);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composerM4659constructorimpl3 = Updater.m4659constructorimpl(composerStartRestartGroup);
            Updater.m4667setimpl(composerM4659constructorimpl3, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4667setimpl(composerM4659constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4667setimpl(composerM4659constructorimpl3, Integer.valueOf(iHashCode3), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4665reconcileimpl(composerM4659constructorimpl3, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4667setimpl(composerM4659constructorimpl3, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 735292256, "C:UserProfilesPage.kt#v7e3id");
            composerStartRestartGroup.startReplaceGroup(-2054490195);
            ComposerKt.sourceInformation(composerStartRestartGroup, "*128@4974L21,131@5117L27,127@4917L250");
            int i5 = 0;
            for (Object obj : UserProfilesPage$lambda$0(stateCollectAsState).getProfiles()) {
                int i6 = i5 + 1;
                if (i5 < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                final UserProfile userProfile = (UserProfile) obj;
                Modifier modifierAutoFocus = ModifierExtKt.autoFocus(Modifier.INSTANCE, i5 == 0, composerStartRestartGroup, 6, 0);
                String str2 = str;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1735474094, str2);
                final Function1 function3 = function2;
                boolean zChanged3 = composerStartRestartGroup.changed(function3) | composerStartRestartGroup.changedInstance(userProfile);
                Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                if (zChanged3 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue4 = new Function0() { // from class: com.stremio.tv.views.userprofiles.UserProfilesPageKt$$ExternalSyntheticLambda7
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return UserProfilesPageKt.UserProfilesPage$lambda$8$0$0$0$0$0(function3, userProfile);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ProfileItem(modifierAutoFocus, userProfile, z, (Function0) objRememberedValue4, composerStartRestartGroup, 0, 0);
                i5 = i6;
                str = str2;
                function2 = function3;
            }
            String str3 = str;
            composerStartRestartGroup.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composerStartRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            String str4 = strings.getLabel_user_profiles_not_you() + ServerSentEventKt.SPACE + strings.getLabel_log_out();
            Integer numValueOf = Integer.valueOf(Icons.INSTANCE.getExit());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1245828473, str3);
            boolean zChangedInstance2 = ((i4 & 896) == 256 || ((i4 & 512) != 0 && composerStartRestartGroup.changedInstance(loginViewModel))) | composerStartRestartGroup.changedInstance(navController);
            Object objRememberedValue5 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance2 || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue5 = new Function0() { // from class: com.stremio.tv.views.userprofiles.UserProfilesPageKt$$ExternalSyntheticLambda8
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return UserProfilesPageKt.UserProfilesPage$lambda$8$0$1$0(loginViewModel, navController);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ButtonKt.Button(null, numValueOf, str4, false, false, false, (Function0) objRememberedValue5, composerStartRestartGroup, 0, 57);
            composerStartRestartGroup = composerStartRestartGroup;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composerStartRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final UserProfile userProfileUserProfilesPage$lambda$3 = UserProfilesPage$lambda$3(mutableState);
            if (userProfileUserProfilesPage$lambda$3 != null) {
                composerStartRestartGroup.startReplaceGroup(-2009460961);
                ComposerKt.sourceInformation(composerStartRestartGroup, "*154@5851L99,157@5980L125,151@5724L396");
                String name = userProfileUserProfilesPage$lambda$3.getName();
                String strUserProfilesPage$lambda$1 = UserProfilesPage$lambda$1(stateCollectAsState2);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1169175956, str3);
                boolean zChangedInstance3 = composerStartRestartGroup.changedInstance(userProfileUserProfilesPage$lambda$3) | (i3 == 32 || ((i4 & 64) != 0 && composerStartRestartGroup.changedInstance(userProfilesViewModel)));
                Object objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                if (zChangedInstance3 || objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue6 = new Function1() { // from class: com.stremio.tv.views.userprofiles.UserProfilesPageKt$$ExternalSyntheticLambda9
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return UserProfilesPageKt.UserProfilesPage$lambda$8$1$0$0(userProfilesViewModel, userProfileUserProfilesPage$lambda$3, (String) obj2);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
                }
                Function1 function4 = (Function1) objRememberedValue6;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1169171802, str3);
                boolean z2 = i3 == 32 || ((i4 & 64) != 0 && composerStartRestartGroup.changedInstance(userProfilesViewModel));
                Object objRememberedValue7 = composerStartRestartGroup.rememberedValue();
                if (z2 || objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue7 = new Function0() { // from class: com.stremio.tv.views.userprofiles.UserProfilesPageKt$$ExternalSyntheticLambda10
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return UserProfilesPageKt.UserProfilesPage$lambda$8$1$1$0(userProfilesViewModel, mutableState);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue7);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                PinDialog(name, strUserProfilesPage$lambda$1, function4, (Function0) objRememberedValue7, composerStartRestartGroup, 0);
                Unit unit2 = Unit.INSTANCE;
                composerStartRestartGroup.endReplaceGroup();
                Unit unit3 = Unit.INSTANCE;
            } else {
                composerStartRestartGroup.startReplaceGroup(-2009460962);
                composerStartRestartGroup.endReplaceGroup();
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composerStartRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.stremio.tv.views.userprofiles.UserProfilesPageKt$$ExternalSyntheticLambda11
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    return UserProfilesPageKt.UserProfilesPage$lambda$9(navController, userProfilesViewModel, loginViewModel, i, (Composer) obj2, ((Integer) obj3).intValue());
                }
            });
        }
    }

    private static final UserProfile UserProfilesPage$lambda$3(MutableState<UserProfile> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final NavController UserProfilesPage$lambda$5$0(NavController navController) {
        navController.popBackStack();
        navController.navigate(R.id.board);
        return navController;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit UserProfilesPage$lambda$7$0(Function0 function0, UserProfilesViewModel userProfilesViewModel, MutableState mutableState, UserProfile profile) {
        Intrinsics.checkNotNullParameter(profile, "profile");
        if (Intrinsics.areEqual((Object) profile.getSelected(), (Object) true) && !profile.getHasPin()) {
            function0.invoke();
        } else if (profile.getHasPin()) {
            userProfilesViewModel.clearProfileSwitchError();
            mutableState.setValue(profile);
        } else {
            UserProfilesViewModel.switchProfile$default(userProfilesViewModel, Intrinsics.areEqual((Object) profile.isMaster(), (Object) true) ? null : profile.getId(), null, 2, null);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit UserProfilesPage$lambda$8$0$0$0$0$0(Function1 function1, UserProfile userProfile) {
        function1.invoke(userProfile);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit UserProfilesPage$lambda$8$0$1$0(LoginViewModel loginViewModel, final NavController navController) {
        loginViewModel.logout(new Function0() { // from class: com.stremio.tv.views.userprofiles.UserProfilesPageKt$$ExternalSyntheticLambda13
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return UserProfilesPageKt.UserProfilesPage$lambda$8$0$1$0$0(navController);
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit UserProfilesPage$lambda$8$0$1$0$0(NavController navController) {
        navController.popBackStack(R.id.main_navigation, true);
        navController.navigate(R.id.login);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit UserProfilesPage$lambda$8$1$0$0(UserProfilesViewModel userProfilesViewModel, UserProfile userProfile, String pin) {
        Intrinsics.checkNotNullParameter(pin, "pin");
        userProfilesViewModel.switchProfile(userProfile.getId(), pin);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit UserProfilesPage$lambda$8$1$1$0(UserProfilesViewModel userProfilesViewModel, MutableState mutableState) {
        mutableState.setValue(null);
        userProfilesViewModel.clearProfileSwitchError();
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:101:0x0203  */
    /* JADX WARN: Code duplicated, block: B:102:0x0207  */
    /* JADX WARN: Code duplicated, block: B:105:0x02a6  */
    /* JADX WARN: Code duplicated, block: B:108:0x02b2  */
    /* JADX WARN: Code duplicated, block: B:109:0x02b6  */
    /* JADX WARN: Code duplicated, block: B:112:0x0353  */
    /* JADX WARN: Code duplicated, block: B:113:0x0358  */
    /* JADX WARN: Code duplicated, block: B:116:0x0360  */
    /* JADX WARN: Code duplicated, block: B:117:0x0365  */
    /* JADX WARN: Code duplicated, block: B:120:0x03b1  */
    /* JADX WARN: Code duplicated, block: B:122:0x03b7  */
    /* JADX WARN: Code duplicated, block: B:125:0x03c1  */
    /* JADX WARN: Code duplicated, block: B:127:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:30:0x0065  */
    /* JADX WARN: Code duplicated, block: B:32:0x006b  */
    /* JADX WARN: Code duplicated, block: B:33:0x006e  */
    /* JADX WARN: Code duplicated, block: B:37:0x0079  */
    /* JADX WARN: Code duplicated, block: B:38:0x007b  */
    /* JADX WARN: Code duplicated, block: B:41:0x0084 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:42:0x0086  */
    /* JADX WARN: Code duplicated, block: B:43:0x0089  */
    /* JADX WARN: Code duplicated, block: B:46:0x0091  */
    /* JADX WARN: Code duplicated, block: B:49:0x00ab  */
    /* JADX WARN: Code duplicated, block: B:52:0x00cd  */
    /* JADX WARN: Code duplicated, block: B:53:0x00d0  */
    /* JADX WARN: Code duplicated, block: B:56:0x00d8  */
    /* JADX WARN: Code duplicated, block: B:57:0x00db  */
    /* JADX WARN: Code duplicated, block: B:60:0x00e3  */
    /* JADX WARN: Code duplicated, block: B:61:0x00e6  */
    /* JADX WARN: Code duplicated, block: B:64:0x00ee  */
    /* JADX WARN: Code duplicated, block: B:65:0x00f1  */
    /* JADX WARN: Code duplicated, block: B:68:0x00f9  */
    /* JADX WARN: Code duplicated, block: B:70:0x0102  */
    /* JADX WARN: Code duplicated, block: B:72:0x0108  */
    /* JADX WARN: Code duplicated, block: B:73:0x010f  */
    /* JADX WARN: Code duplicated, block: B:76:0x0138  */
    /* JADX WARN: Code duplicated, block: B:77:0x013f  */
    /* JADX WARN: Code duplicated, block: B:80:0x0163  */
    /* JADX WARN: Code duplicated, block: B:81:0x0165  */
    /* JADX WARN: Code duplicated, block: B:86:0x0174  */
    /* JADX WARN: Code duplicated, block: B:89:0x0191  */
    /* JADX WARN: Code duplicated, block: B:90:0x0193  */
    /* JADX WARN: Code duplicated, block: B:95:0x01a2  */
    /* JADX WARN: Code duplicated, block: B:98:0x01f7  */
    public static final void ProfileItem(final Modifier modifier, final UserProfile profile, boolean z, final Function0<Unit> onClick, Composer composer, final int i, final int i2) {
        int i3;
        boolean z2;
        boolean z3;
        final boolean z4;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        boolean z5;
        Object objRememberedValue;
        State<Boolean> stateCollectIsFocusedAsState;
        boolean zAreEqual;
        float f;
        float f2;
        float f3;
        float f4;
        long jM5603getTransparent0d7_KjU;
        long jM5603getTransparent0d7_KjU2;
        int i4;
        boolean z6;
        UserProfilesPageKt$ProfileItem$1$1 userProfilesPageKt$ProfileItem$1$1RememberedValue;
        boolean z7;
        Object objRememberedValue2;
        Function0<ComposeUiNode> constructor;
        Function0<ComposeUiNode> constructor2;
        FontWeight.Companion companion;
        FontWeight normal;
        Colors colors;
        long jM11515getPrimaryForeground0d7_KjU;
        int i5;
        Intrinsics.checkNotNullParameter(modifier, "modifier");
        Intrinsics.checkNotNullParameter(profile, "profile");
        Intrinsics.checkNotNullParameter(onClick, "onClick");
        Composer composerStartRestartGroup = composer.startRestartGroup(-1299221382);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(ProfileItem)N(modifier,profile,compact,onClick)173@6315L39,174@6394L25,196@7246L35,197@7306L235,188@6836L1583:UserProfilesPage.kt#v7e3id");
        if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changed(modifier) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if ((i & 48) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(profile) ? 32 : 16;
        }
        int i6 = i2 & 4;
        if (i6 == 0) {
            if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                z2 = z;
                i3 |= composerStartRestartGroup.changed(z2) ? 256 : 128;
            }
            if ((i & 3072) == 0) {
                if (composerStartRestartGroup.changedInstance(onClick)) {
                    i5 = 2048;
                } else {
                    i5 = 1024;
                }
                i3 |= i5;
            }
            if ((i3 & 1171) != 1170) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                composerStartRestartGroup.skipToGroupEnd();
                z4 = z2;
            } else {
                if (i6 != 0) {
                    z5 = false;
                } else {
                    z5 = z2;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1299221382, i3, -1, "com.stremio.tv.views.userprofiles.ProfileItem (UserProfilesPage.kt:172)");
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1283740001, "CC(remember):UserProfilesPage.kt#9igjgp");
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                MutableInteractionSource mutableInteractionSource = (MutableInteractionSource) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                stateCollectIsFocusedAsState = FocusInteractionKt.collectIsFocusedAsState(mutableInteractionSource, composerStartRestartGroup, 6);
                zAreEqual = Intrinsics.areEqual((Object) profile.getSelected(), (Object) true);
                if (z5) {
                    f = 72.0f;
                } else {
                    f = 100.0f;
                }
                float fM8482constructorimpl = Dp.m8482constructorimpl(f);
                if (z5) {
                    f2 = 110.0f;
                } else {
                    f2 = 150.0f;
                }
                float fM8482constructorimpl2 = Dp.m8482constructorimpl(f2);
                if (z5) {
                    f3 = 10.0f;
                } else {
                    f3 = 16.0f;
                }
                float fM8482constructorimpl3 = Dp.m8482constructorimpl(f3);
                if (z5) {
                    f4 = 8.0f;
                } else {
                    f4 = 12.0f;
                }
                float fM8482constructorimpl4 = Dp.m8482constructorimpl(f4);
                if (zAreEqual) {
                    jM5603getTransparent0d7_KjU = Colors.INSTANCE.m11513getPrimaryAccent0d7_KjU();
                } else if (ProfileItem$lambda$1(stateCollectIsFocusedAsState)) {
                    jM5603getTransparent0d7_KjU = Colors.INSTANCE.m11517getSecondaryBackground0d7_KjU();
                } else {
                    jM5603getTransparent0d7_KjU = Color.INSTANCE.m5603getTransparent0d7_KjU();
                }
                long j = jM5603getTransparent0d7_KjU;
                Alignment.Horizontal centerHorizontally = Alignment.INSTANCE.getCenterHorizontally();
                Arrangement.HorizontalOrVertical horizontalOrVerticalM809spacedBy0680j_4 = Arrangement.INSTANCE.m809spacedBy0680j_4(fM8482constructorimpl4);
                Modifier modifierClip = ClipKt.clip(SizeKt.m1189width3ABfNKs(modifier, fM8482constructorimpl2), Shapes.INSTANCE.getRoundedCorner());
                if (ProfileItem$lambda$1(stateCollectIsFocusedAsState)) {
                    jM5603getTransparent0d7_KjU2 = Colors.INSTANCE.m11520getTertiaryBackground0d7_KjU();
                } else {
                    jM5603getTransparent0d7_KjU2 = Color.INSTANCE.m5603getTransparent0d7_KjU();
                }
                Modifier modifierFocusable$default = FocusableKt.focusable$default(BackgroundKt.m342backgroundbw27NRU$default(modifierClip, jM5603getTransparent0d7_KjU2, null, 2, null), false, mutableInteractionSource, 1, null);
                Unit unit = Unit.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1283769789, "CC(remember):UserProfilesPage.kt#9igjgp");
                i4 = i3 & 7168;
                if (i4 == 2048) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                userProfilesPageKt$ProfileItem$1$1RememberedValue = composerStartRestartGroup.rememberedValue();
                if (!z6 || userProfilesPageKt$ProfileItem$1$1RememberedValue == Composer.INSTANCE.getEmpty()) {
                    userProfilesPageKt$ProfileItem$1$1RememberedValue = new UserProfilesPageKt$ProfileItem$1$1(onClick);
                    composerStartRestartGroup.updateRememberedValue(userProfilesPageKt$ProfileItem$1$1RememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Modifier modifierPointerInput = SuspendingPointerInputFilterKt.pointerInput(modifierFocusable$default, unit, (PointerInputEventHandler) userProfilesPageKt$ProfileItem$1$1RememberedValue);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1283771909, "CC(remember):UserProfilesPage.kt#9igjgp");
                if (i4 == 2048) {
                    z7 = true;
                } else {
                    z7 = false;
                }
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (!z7 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue2 = (Function1) new Function1<KeyEvent, Boolean>() { // from class: com.stremio.tv.views.userprofiles.UserProfilesPageKt$ProfileItem$2$1
                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Boolean invoke(KeyEvent keyEvent) {
                            return m11660invokeZmokQxo(keyEvent.m6760unboximpl());
                        }

                        /* JADX INFO: renamed from: invoke-ZmokQxo, reason: not valid java name */
                        public final Boolean m11660invokeZmokQxo(android.view.KeyEvent it) {
                            Intrinsics.checkNotNullParameter(it, "it");
                            if (KeyEventType.m6764equalsimpl0(KeyEvent_androidKt.m6772getTypeZmokQxo(it), KeyEventType.Companion.m6768getKeyDownCS__XNY()) && (Key.m6451equalsimpl0(KeyEvent_androidKt.m6771getKeyZmokQxo(it), Key.INSTANCE.m6524getDirectionCenterEK5gGoQ()) || Key.m6451equalsimpl0(KeyEvent_androidKt.m6771getKeyZmokQxo(it), Key.INSTANCE.m6538getEnterEK5gGoQ()))) {
                                onClick.invoke();
                            }
                            return false;
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Modifier modifierM1117padding3ABfNKs = PaddingKt.m1117padding3ABfNKs(KeyInputModifierKt.onKeyEvent(modifierPointerInput, (Function1) objRememberedValue2), fM8482constructorimpl3);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(horizontalOrVerticalM809spacedBy0680j_4, centerHorizontally, composerStartRestartGroup, 48);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
                int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM1117padding3ABfNKs);
                constructor = ComposeUiNode.INSTANCE.getConstructor();
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
                Updater.m4667setimpl(composerM4659constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4667setimpl(composerM4659constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4667setimpl(composerM4659constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4665reconcileimpl(composerM4659constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4667setimpl(composerM4659constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 939299275, "C207@7593L487,225@8149L16,223@8090L323:UserProfilesPage.kt#v7e3id");
                Modifier modifierM353borderxT4_qwU = BorderKt.m353borderxT4_qwU(SizeKt.m1184size3ABfNKs(Modifier.INSTANCE, fM8482constructorimpl), Dp.m8482constructorimpl(3.0f), j, RoundedCornerShapeKt.getCircleShape());
                Alignment center = Alignment.INSTANCE.getCenter();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
                int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM353borderxT4_qwU);
                constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16216L9:Composables.kt#9igjgp");
                if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor2);
                } else {
                    composerStartRestartGroup.useNode();
                }
                Composer composerM4659constructorimpl2 = Updater.m4659constructorimpl(composerStartRestartGroup);
                Updater.m4667setimpl(composerM4659constructorimpl2, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4667setimpl(composerM4659constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4667setimpl(composerM4659constructorimpl2, Integer.valueOf(iHashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4665reconcileimpl(composerM4659constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4667setimpl(composerM4659constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1581614479, "C213@7794L276:UserProfilesPage.kt#v7e3id");
                RemoteImageKt.RemoteImage(ClipKt.clip(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), RoundedCornerShapeKt.getCircleShape()), AvatarKt.getAvatarUrl(profile.getAvatar()), profile.getName(), ContentScale.INSTANCE.getCrop(), null, 0.0f, false, null, composerStartRestartGroup, 3072, PsExtractor.VIDEO_STREAM_MASK);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composerStartRestartGroup.endNode();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                String name = profile.getName();
                TextStyle textStyleLabelTextStyle = ThemeKt.labelTextStyle(composerStartRestartGroup, 0);
                companion = FontWeight.INSTANCE;
                if (zAreEqual) {
                    normal = companion.getBold();
                } else {
                    normal = companion.getNormal();
                }
                colors = Colors.INSTANCE;
                if (zAreEqual) {
                    jM11515getPrimaryForeground0d7_KjU = colors.m11513getPrimaryAccent0d7_KjU();
                } else {
                    jM11515getPrimaryForeground0d7_KjU = colors.m11515getPrimaryForeground0d7_KjU();
                }
                TextKt.m3353TextNvy7gAk(name, null, jM11515getPrimaryForeground0d7_KjU, null, 0L, null, normal, null, 0L, null, TextAlign.m8353boximpl(TextAlign.INSTANCE.m8360getCentere0LSkKk()), 0L, 0, false, 1, 0, null, textStyleLabelTextStyle, composerStartRestartGroup, 0, 24576, 113594);
                composerStartRestartGroup = composerStartRestartGroup;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composerStartRestartGroup.endNode();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                z4 = z5;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.stremio.tv.views.userprofiles.UserProfilesPageKt$$ExternalSyntheticLambda14
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return UserProfilesPageKt.ProfileItem$lambda$5(modifier, profile, z4, onClick, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= RendererCapabilities.DECODER_SUPPORT_MASK;
        z2 = z;
        if ((i & 3072) == 0) {
        

... [truncated: exceeds 60000 bytes, total 135319 bytes]