package com.stremio.tv.views.menu;

import android.os.Bundle;
import androidx.activity.compose.BackHandlerKt;
import androidx.compose.animation.SingleValueAnimationKt;
import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.FocusableKt;
import androidx.compose.foundation.ImageKt;
import androidx.compose.foundation.interaction.FocusInteractionKt;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
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
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.focus.FocusChangedModifierKt;
import androidx.compose.ui.focus.FocusEnterExitScope;
import androidx.compose.ui.focus.FocusProperties;
import androidx.compose.ui.focus.FocusPropertiesKt;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.focus.FocusRequesterModifierKt;
import androidx.compose.ui.focus.FocusState;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.painter.Painter;
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
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.res.PainterResources_androidKt;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.media3.extractor.ts.PsExtractor;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import com.google.android.gms.common.Scopes;
import com.stremio.common.composables.AvatarKt;
import com.stremio.common.composables.IconKt;
import com.stremio.common.composables.animations.FadeInOutKt;
import com.stremio.common.extensions.ModifierExtKt;
import com.stremio.common.translations.StringsKt;
import com.stremio.core.models.UserProfile;
import com.stremio.icons.Icons;
import com.stremio.translations.Strings;
import com.stremio.tv.Colors;
import com.stremio.tv.R;
import com.stremio.tv.Shapes;
import com.stremio.tv.Sizes;
import com.stremio.tv.ThemeKt;
import com.stremio.tv.composables.RemoteImageKt;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: NavigationMenu.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000l\n\u0000\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001aE\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000bH\u0007b\u0002\b\u000eb\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011¢\u0006\u0002\u0010\r\u001a7\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\u0017H\u0003b\u0002\b\u000eb\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011¢\u0006\u0002\u0010\u0018\u001am\u0010\u0019\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u000b2\b\u0010\u001a\u001a\u0004\u0018\u00010\u00022\b\u0010\u001b\u001a\u0004\u0018\u00010\u00022\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00050\u001d2\u0012\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00050\u001dH\u0003b\u0002\b\u000eb\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011¢\u0006\u0002\u0010\u001f\u001a]\u0010 \u001a\u00020\u00052\u0006\u0010!\u001a\u00020\"2\u0006\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u001a\u001a\u00020\u000b2\b\u0010#\u001a\u0004\u0018\u00010$2\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00050&2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00050&H\u0003b\u0002\b\u000eb\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011¢\u0006\u0002\u0010(\u001aO\u0010)\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010*\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0015\u001a\u00020\u000b2\u0006\u0010+\u001a\u00020\u000b2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00050&H\u0003b\u0002\b\u000eb\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011¢\u0006\u0002\u0010,\"\u0014\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006-²\u0006\f\u0010\u001a\u001a\u0004\u0018\u00010\u0002X\u008a\u008e\u0002²\u0006\f\u0010\u001b\u001a\u0004\u0018\u00010\u0002X\u008a\u008e\u0002²\u0006\n\u0010\u0015\u001a\u00020\u000bX\u008a\u008e\u0002²\u0006\n\u0010.\u001a\u00020/X\u008a\u0084\u0002²\u0006\n\u00100\u001a\u000201X\u008a\u0084\u0002²\u0006\n\u00102\u001a\u00020\u000bX\u008a\u0084\u0002²\u0006\n\u00102\u001a\u00020\u000bX\u008a\u0084\u0002"}, d2 = {"MAIN_ROUTES", "", "", "VISIBLE_ROUTES", "NavigationMenu", "", "navController", "Landroidx/navigation/NavController;", "activeProfile", "Lcom/stremio/core/models/UserProfile;", "hasProfiles", "", "hasPremium", "(Landroidx/navigation/NavController;Lcom/stremio/core/models/UserProfile;ZZLandroidx/compose/runtime/Composer;II)V", "Landroidx/compose/runtime/Composable;", "Landroidx/compose/runtime/ComposableTarget;", "applier", "androidx.compose.ui.UiComposable", "Logo", "modifier", "Landroidx/compose/ui/Modifier;", "expanded", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "(Landroidx/compose/ui/Modifier;ZLandroidx/compose/ui/unit/LayoutDirection;Landroidx/compose/runtime/Composer;I)V", "Tabs", "selected", "focused", "onTabFocus", "Lkotlin/Function1;", "onTabChange", "(ZLjava/lang/Integer;Ljava/lang/Integer;Lcom/stremio/core/models/UserProfile;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;I)V", "Tab", "tab", "Lcom/stremio/tv/views/menu/Tab;", "focusRequester", "Landroidx/compose/ui/focus/FocusRequester;", "onFocus", "Lkotlin/Function0;", "onClick", "(Lcom/stremio/tv/views/menu/Tab;ZZLandroidx/compose/ui/focus/FocusRequester;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "ProfileSection", Scopes.PROFILE, "isSelected", "(Landroidx/compose/ui/Modifier;Lcom/stremio/core/models/UserProfile;ZZLkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "Stremio:androidTV-com.stremio.one-1.11.2-40000002_release", "backgroundWidth", "Landroidx/compose/ui/unit/Dp;", TtmlNode.ATTR_TTS_BACKGROUND_COLOR, "Landroidx/compose/ui/graphics/Color;", "isFocused"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class NavigationMenuKt {
    private static final List<Integer> MAIN_ROUTES = CollectionsKt.listOf((Object[]) new Integer[]{Integer.valueOf(R.id.search), Integer.valueOf(R.id.board), Integer.valueOf(R.id.discover), Integer.valueOf(R.id.library), Integer.valueOf(R.id.addons), Integer.valueOf(R.id.settings)});
    private static final List<Integer> VISIBLE_ROUTES = CollectionsKt.listOf((Object[]) new Integer[]{Integer.valueOf(R.id.search), Integer.valueOf(R.id.board), Integer.valueOf(R.id.discover), Integer.valueOf(R.id.library), Integer.valueOf(R.id.meta_details), Integer.valueOf(R.id.magnet), Integer.valueOf(R.id.settings), Integer.valueOf(R.id.addons), Integer.valueOf(R.id.addon_details)});

    static final Unit Logo$lambda$1(Modifier modifier, boolean z, LayoutDirection layoutDirection, int i, Composer composer, int i2) {
        Logo(modifier, z, layoutDirection, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit NavigationMenu$lambda$16(NavController navController, UserProfile userProfile, boolean z, boolean z2, int i, int i2, Composer composer, int i3) {
        NavigationMenu(navController, userProfile, z, z2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit ProfileSection$lambda$5(Modifier modifier, UserProfile userProfile, boolean z, boolean z2, Function0 function0, int i, Composer composer, int i2) {
        ProfileSection(modifier, userProfile, z, z2, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit Tab$lambda$5(Tab tab, boolean z, boolean z2, FocusRequester focusRequester, Function0 function0, Function0 function1, int i, Composer composer, int i2) {
        Tab(tab, z, z2, focusRequester, function0, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit Tabs$lambda$7(boolean z, Integer num, Integer num2, UserProfile userProfile, Function1 function1, Function1 function2, int i, Composer composer, int i2) {
        Tabs(z, num, num2, userProfile, function1, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:101:0x0281  */
    /* JADX WARN: Code duplicated, block: B:104:0x028d  */
    /* JADX WARN: Code duplicated, block: B:106:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:23:0x0049  */
    /* JADX WARN: Code duplicated, block: B:25:0x004f  */
    /* JADX WARN: Code duplicated, block: B:26:0x0052  */
    /* JADX WARN: Code duplicated, block: B:30:0x0059  */
    /* JADX WARN: Code duplicated, block: B:31:0x005c  */
    /* JADX WARN: Code duplicated, block: B:33:0x0060  */
    /* JADX WARN: Code duplicated, block: B:35:0x0068  */
    /* JADX WARN: Code duplicated, block: B:36:0x006b  */
    /* JADX WARN: Code duplicated, block: B:41:0x0079  */
    /* JADX WARN: Code duplicated, block: B:42:0x007b  */
    /* JADX WARN: Code duplicated, block: B:45:0x0084 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:46:0x0086  */
    /* JADX WARN: Code duplicated, block: B:47:0x0089  */
    /* JADX WARN: Code duplicated, block: B:49:0x008c  */
    /* JADX WARN: Code duplicated, block: B:50:0x008e  */
    /* JADX WARN: Code duplicated, block: B:53:0x0095  */
    /* JADX WARN: Code duplicated, block: B:56:0x00c8  */
    /* JADX WARN: Code duplicated, block: B:59:0x00f1  */
    /* JADX WARN: Code duplicated, block: B:62:0x0118  */
    /* JADX WARN: Code duplicated, block: B:65:0x0140  */
    /* JADX WARN: Code duplicated, block: B:67:0x0148  */
    /* JADX WARN: Code duplicated, block: B:70:0x016a  */
    /* JADX WARN: Code duplicated, block: B:71:0x0171 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:72:0x0173  */
    /* JADX WARN: Code duplicated, block: B:75:0x019b  */
    /* JADX WARN: Code duplicated, block: B:76:0x01a2 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:77:0x01a4  */
    /* JADX WARN: Code duplicated, block: B:80:0x01cd  */
    /* JADX WARN: Code duplicated, block: B:82:0x01d5  */
    /* JADX WARN: Code duplicated, block: B:85:0x01fa  */
    /* JADX WARN: Code duplicated, block: B:87:0x0202  */
    /* JADX WARN: Code duplicated, block: B:90:0x022c  */
    /* JADX WARN: Code duplicated, block: B:92:0x0234  */
    /* JADX WARN: Code duplicated, block: B:95:0x026e  */
    /* JADX WARN: Code duplicated, block: B:97:0x0275  */
    /* JADX WARN: Code duplicated, block: B:99:0x027b  */
    public static final void NavigationMenu(final NavController navController, UserProfile userProfile, final boolean z, boolean z2, Composer composer, final int i, final int i2) {
        int i3;
        UserProfile userProfile2;
        int i4;
        boolean z3;
        int i5;
        boolean z4;
        Composer composer2;
        final UserProfile userProfile3;
        final boolean z5;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        UserProfile userProfile4;
        boolean z6;
        Object objRememberedValue;
        final MutableState mutableState;
        Object objRememberedValue2;
        final MutableState mutableState2;
        Object objRememberedValue3;
        boolean zChanged;
        Object objRememberedValue4;
        boolean zNavigationMenu$lambda$7;
        float fM11528getNavBarWidthD9Ej5fM;
        boolean zNavigationMenu$lambda$8;
        long jM5603getTransparent0d7_KjU;
        boolean zChanged2;
        Object objRememberedValue5;
        boolean zChanged3;
        Object objRememberedValue6;
        boolean zChanged4;
        Object objRememberedValue7;
        int i6;
        Intrinsics.checkNotNullParameter(navController, "navController");
        Composer composerStartRestartGroup = composer.startRestartGroup(-18498152);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(NavigationMenu)N(navController,activeProfile,hasProfiles,hasPremium)116@4616L7,118@4662L30,118@4645L47,119@4729L36,119@4712L53,120@4803L25,120@4786L42,122@4848L56,124@4933L126,131@5088L147,138@5273L28,142@5340L215,151@5584L422,151@5561L445,166@6031L1828,166@6012L1847:NavigationMenu.kt#wivmcv");
        if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changedInstance(navController) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        int i7 = i2 & 2;
        if (i7 == 0) {
            if ((i & 48) == 0) {
                userProfile2 = userProfile;
                i3 |= composerStartRestartGroup.changedInstance(userProfile2) ? 32 : 16;
            }
            if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                if (composerStartRestartGroup.changed(z)) {
                    i6 = 256;
                } else {
                    i6 = 128;
                }
                i3 |= i6;
            }
            i4 = i2 & 8;
            if (i4 != 0) {
                if ((i & 3072) == 0) {
                    z3 = z2;
                    if (composerStartRestartGroup.changed(z3)) {
                        i5 = 2048;
                    } else {
                        i5 = 1024;
                    }
                    i3 |= i5;
                }
                if ((i3 & 1171) != 1170) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z4, i3 & 1)) {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    userProfile3 = userProfile2;
                    z5 = z3;
                } else {
                    if (i7 != 0) {
                        userProfile4 = null;
                    } else {
                        userProfile4 = userProfile2;
                    }
                    if (i4 != 0) {
                        z6 = false;
                    } else {
                        z6 = z3;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-18498152, i3, -1, "com.stremio.tv.views.menu.NavigationMenu (NavigationMenu.kt:115)");
                    }
                    ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                    Object objConsume = composerStartRestartGroup.consume(localLayoutDirection);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    final LayoutDirection layoutDirection = (LayoutDirection) objConsume;
                    Object[] objArr = new Object[0];
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -596846858, "CC(remember):NavigationMenu.kt#9igjgp");
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = new Function0() { // from class: com.stremio.tv.views.menu.NavigationMenuKt$$ExternalSyntheticLambda4
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return NavigationMenuKt.NavigationMenu$lambda$0$0();
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    mutableState = (MutableState) RememberSaveableKt.rememberSaveable(objArr, (Function0) objRememberedValue, composerStartRestartGroup, 48);
                    Object[] objArr2 = new Object[0];
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -596844708, "CC(remember):NavigationMenu.kt#9igjgp");
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue2 = new Function0() { // from class: com.stremio.tv.views.menu.NavigationMenuKt$$ExternalSyntheticLambda5
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return NavigationMenuKt.NavigationMenu$lambda$3$0();
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    mutableState2 = (MutableState) RememberSaveableKt.rememberSaveable(objArr2, (Function0) objRememberedValue2, composerStartRestartGroup, 48);
                    Object[] objArr3 = new Object[0];
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -596842351, "CC(remember):NavigationMenu.kt#9igjgp");
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue3 = new Function0() { // from class: com.stremio.tv.views.menu.NavigationMenuKt$$ExternalSyntheticLambda6
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return NavigationMenuKt.NavigationMenu$lambda$6$0();
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    final MutableState mutableState3 = (MutableState) RememberSaveableKt.rememberSaveable(objArr3, (Function0) objRememberedValue3, composerStartRestartGroup, 48);
                    Integer numNavigationMenu$lambda$1 = NavigationMenu$lambda$1(mutableState);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -596840880, "CC(remember):NavigationMenu.kt#9igjgp");
                    zChanged = composerStartRestartGroup.changed(numNavigationMenu$lambda$1);
                    objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                    if (!zChanged || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue4 = Boolean.valueOf(CollectionsKt.contains(VISIBLE_ROUTES, NavigationMenu$lambda$1(mutableState)));
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                    }
                    boolean zBooleanValue = ((Boolean) objRememberedValue4).booleanValue();
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    zNavigationMenu$lambda$7 = NavigationMenu$lambda$7(mutableState3);
                    if (zNavigationMenu$lambda$7) {
                        fM11528getNavBarWidthD9Ej5fM = Dp.m8482constructorimpl(185.0f);
                    } else {
                        if (!zNavigationMenu$lambda$7) {
                            throw new NoWhenBranchMatchedException();
                        }
                        fM11528getNavBarWidthD9Ej5fM = Sizes.INSTANCE.m11528getNavBarWidthD9Ej5fM();
                    }
                    final UserProfile userProfile5 = userProfile4;
                    final State<Dp> stateM238animateDpAsStateAjpBEmI = AnimateAsStateKt.m238animateDpAsStateAjpBEmI(fM11528getNavBarWidthD9Ej5fM, null, null, null, composerStartRestartGroup, 0, 14);
                    zNavigationMenu$lambda$8 = NavigationMenu$lambda$7(mutableState3);
                    if (zNavigationMenu$lambda$8) {
                        jM5603getTransparent0d7_KjU = Colors.INSTANCE.m11514getPrimaryBackground0d7_KjU();
                    } else {
                        if (!zNavigationMenu$lambda$8) {
                            throw new NoWhenBranchMatchedException();
                        }
                        jM5603getTransparent0d7_KjU = Color.INSTANCE.m5603getTransparent0d7_KjU();
                    }
                    final State<Color> stateM205animateColorAsStateeuL9pac = SingleValueAnimationKt.m205animateColorAsStateeuL9pac(jM5603getTransparent0d7_KjU, null, null, null, composerStartRestartGroup, 0, 14);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -596827308, "CC(remember):NavigationMenu.kt#9igjgp");
                    zChanged2 = composerStartRestartGroup.changed(mutableState2);
                    objRememberedValue5 = composerStartRestartGroup.rememberedValue();
                    if (!zChanged2 || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue5 = new Function1() { // from class: com.stremio.tv.views.menu.NavigationMenuKt$$ExternalSyntheticLambda7
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return NavigationMenuKt.NavigationMenu$lambda$12$0(mutableState2, ((Integer) obj).intValue());
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                    }
                    final Function1 function1 = (Function1) objRememberedValue5;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -596824977, "CC(remember):NavigationMenu.kt#9igjgp");
                    zChanged3 = composerStartRestartGroup.changed(mutableState) | composerStartRestartGroup.changedInstance(navController);
                    objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                    if (!zChanged3 || objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue6 = new Function1() { // from class: com.stremio.tv.views.menu.NavigationMenuKt$$ExternalSyntheticLambda8
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return NavigationMenuKt.NavigationMenu$lambda$13$0(navController, mutableState, ((Integer) obj).intValue());
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
                    }
                    final Function1 function2 = (Function1) objRememberedValue6;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Unit unit = Unit.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -596816962, "CC(remember):NavigationMenu.kt#9igjgp");
                    zChanged4 = composerStartRestartGroup.changed(mutableState) | composerStartRestartGroup.changed(mutableState2) | composerStartRestartGroup.changedInstance(navController);
                    objRememberedValue7 = composerStartRestartGroup.rememberedValue();
                    if (!zChanged4 || objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue7 = new Function1() { // from class: com.stremio.tv.views.menu.NavigationMenuKt$$ExternalSyntheticLambda9
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return NavigationMenuKt.NavigationMenu$lambda$14$0(navController, mutableState, mutableState2, (DisposableEffectScope) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue7);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    EffectsKt.DisposableEffect(unit, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) objRememberedValue7, composerStartRestartGroup, 6);
                    composer2 = composerStartRestartGroup;
                    final boolean z7 = z6;
                    FadeInOutKt.FadeInOut(zBooleanValue, ComposableLambdaKt.rememberComposableLambda(-28920841, true, new Function2() { // from class: com.stremio.tv.views.menu.NavigationMenuKt$$ExternalSyntheticLambda10
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj, Object obj2) {
                            return NavigationMenuKt.NavigationMenu$lambda$15(layoutDirection, mutableState3, stateM238animateDpAsStateAjpBEmI, stateM205animateColorAsStateeuL9pac, userProfile5, function1, function2, z7, z, mutableState, navController, mutableState2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    }, composer2, 54), composer2, 48);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    z5 = z7;
                    userProfile3 = userProfile5;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.stremio.tv.views.menu.NavigationMenuKt$$ExternalSyntheticLambda12
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj, Object obj2) {
                            return NavigationMenuKt.NavigationMenu$lambda$16(navController, userProfile3, z, z5, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 3072;
            z3 = z2;
            if ((i3 & 1171) != 1170) {
                z4 = true;
            } else {
                z4 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z4, i3 & 1)) {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                userProfile3 = userProfile2;
                z5 = z3;
            } else {
                if (i7 != 0) {
                    userProfile4 = null;
                } else {
                    userProfile4 = userProfile2;
                }
                if (i4 != 0) {
                    z6 = false;
                } else {
                    z6 = z3;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-18498152, i3, -1, "com.stremio.tv.views.menu.NavigationMenu (NavigationMenu.kt:115)");
                }
                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection2 = CompositionLocalsKt.getLocalLayoutDirection();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                Object objConsume2 = composerStartRestartGroup.consume(localLayoutDirection2);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                final LayoutDirection layoutDirection2 = (LayoutDirection) objConsume2;
                Object[] objArr4 = new Object[0];
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -596846858, "CC(remember):NavigationMenu.kt#9igjgp");
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = new Function0() { // from class: com.stremio.tv.views.menu.NavigationMenuKt$$ExternalSyntheticLambda4
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return NavigationMenuKt.NavigationMenu$lambda$0$0();
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                mutableState = (MutableState) RememberSaveableKt.rememberSaveable(objArr4, (Function0) objRememberedValue, composerStartRestartGroup, 48);
                Object[] objArr5 = new Object[0];
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -596844708, "CC(remember):NavigationMenu.kt#9igjgp");
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue2 = new Function0() { // from class: com.stremio.tv.views.menu.NavigationMenuKt$$ExternalSyntheticLambda5
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return NavigationMenuKt.NavigationMenu$lambda$3$0();
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                mutableState2 = (MutableState) RememberSaveableKt.rememberSaveable(objArr5, (Function0) objRememberedValue2, composerStartRestartGroup, 48);
                Object[] objArr6 = new Object[0];
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -596842351, "CC(remember):NavigationMenu.kt#9igjgp");
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue3 = new Function0() { // from class: com.stremio.tv.views.menu.NavigationMenuKt$$ExternalSyntheticLambda6
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return NavigationMenuKt.NavigationMenu$lambda$6$0();
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                final MutableState mutableState4 = (MutableState) RememberSaveableKt.rememberSaveable(objArr6, (Function0) objRememberedValue3, composerStartRestartGroup, 48);
                Integer numNavigationMenu$lambda$2 = NavigationMenu$lambda$1(mutableState);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -596840880, "CC(remember):NavigationMenu.kt#9igjgp");
                zChanged = composerStartRestartGroup.changed(numNavigationMenu$lambda$2);
                objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                if (!zChanged) {
                    objRememberedValue4 = Boolean.valueOf(CollectionsKt.contains(VISIBLE_ROUTES, NavigationMenu$lambda$1(mutableState)));
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                } else {
                    objRememberedValue4 = Boolean.valueOf(CollectionsKt.contains(VISIBLE_ROUTES, NavigationMenu$lambda$1(mutableState)));
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                }
                boolean zBooleanValue2 = ((Boolean) objRememberedValue4).booleanValue();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                zNavigationMenu$lambda$7 = NavigationMenu$lambda$7(mutableState4);
                if (zNavigationMenu$lambda$7) {
                    fM11528getNavBarWidthD9Ej5fM = Dp.m8482constructorimpl(185.0f);
                } else {
                    if (!zNavigationMenu$lambda$7) {
                        throw new NoWhenBranchMatchedException();
                    }
                    fM11528getNavBarWidthD9Ej5fM = Sizes.INSTANCE.m11528getNavBarWidthD9Ej5fM();
                }
                final UserProfile userProfile6 = userProfile4;
                final State stateM238animateDpAsStateAjpBEmI2 = AnimateAsStateKt.m238animateDpAsStateAjpBEmI(fM11528getNavBarWidthD9Ej5fM, null, null, null, composerStartRestartGroup, 0, 14);
                zNavigationMenu$lambda$8 = NavigationMenu$lambda$7(mutableState4);
                if (zNavigationMenu$lambda$8) {
                    jM5603getTransparent0d7_KjU = Colors.INSTANCE.m11514getPrimaryBackground0d7_KjU();
                } else {
                    if (!zNavigationMenu$lambda$8) {
                        throw new NoWhenBranchMatchedException();
                    }
                    jM5603getTransparent0d7_KjU = Color.INSTANCE.m5603getTransparent0d7_KjU();
                }
                final State stateM205animateColorAsStateeuL9pac2 = SingleValueAnimationKt.m205animateColorAsStateeuL9pac(jM5603getTransparent0d7_KjU, null, null, null, composerStartRestartGroup, 0, 14);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -596827308, "CC(remember):NavigationMenu.kt#9igjgp");
                zChanged2 = composerStartRestartGroup.changed(mutableState2);
                objRememberedValue5 = composerStartRestartGroup.rememberedValue();
                if (!zChanged2) {
                    objRememberedValue5 = new Function1() { // from class: com.stremio.tv.views.menu.NavigationMenuKt$$ExternalSyntheticLambda7
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return NavigationMenuKt.NavigationMenu$lambda$12$0(mutableState2, ((Integer) obj).intValue());
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                } else {
                    objRememberedValue5 = new Function1() { // from class: com.stremio.tv.views.menu.NavigationMenuKt$$ExternalSyntheticLambda7
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return NavigationMenuKt.NavigationMenu$lambda$12$0(mutableState2, ((Integer) obj).intValue());
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                }
                final Function1 function3 = (Function1) objRememberedValue5;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -596824977, "CC(remember):NavigationMenu.kt#9igjgp");
                zChanged3 = composerStartRestartGroup.changed(mutableState) | composerStartRestartGroup.changedInstance(navController);
                objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                if (!zChanged3) {
                    objRememberedValue6 = new Function1() { // from class: com.stremio.tv.views.menu.NavigationMenuKt$$ExternalSyntheticLambda8
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return NavigationMenuKt.NavigationMenu$lambda$13$0(navController, mutableState, ((Integer) obj).intValue());
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
                } else {
                    objRememberedValue6 = new Function1() { // from class: com.stremio.tv.views.menu.NavigationMenuKt$$ExternalSyntheticLambda8
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return NavigationMenuKt.NavigationMenu$lambda$13$0(navController, mutableState, ((Integer) obj).intValue());
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
                }
                final Function1 function4 = (Function1) objRememberedValue6;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Unit unit2 = Unit.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -596816962, "CC(remember):NavigationMenu.kt#9igjgp");
                zChanged4 = composerStartRestartGroup.changed(mutableState) | composerStartRestartGroup.changed(mutableState2) | composerStartRestartGroup.changedInstance(navController);
                objRememberedValue7 = composerStartRestartGroup.rememberedValue();
                if (!zChanged4) {
                    objRememberedValue7 = new Function1() { // from class: com.stremio.tv.views.menu.NavigationMenuKt$$ExternalSyntheticLambda9
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return NavigationMenuKt.NavigationMenu$lambda$14$0(navController, mutableState, mutableState2, (DisposableEffectScope) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue7);
                } else {
                    objRememberedValue7 = new Function1() { // from class: com.stremio.tv.views.menu.NavigationMenuKt$$ExternalSyntheticLambda9
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return NavigationMenuKt.NavigationMenu$lambda$14$0(navController, mutableState, mutableState2, (DisposableEffectScope) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue7);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                EffectsKt.DisposableEffect(unit2, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) objRememberedValue7, composerStartRestartGroup, 6);
                composer2 = composerStartRestartGroup;
                final boolean z8 = z6;
                FadeInOutKt.FadeInOut(zBooleanValue2, ComposableLambdaKt.rememberComposableLambda(-28920841, true, new Function2() { // from class: com.stremio.tv.views.menu.NavigationMenuKt$$ExternalSyntheticLambda10
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return NavigationMenuKt.NavigationMenu$lambda$15(layoutDirection2, mutableState4, stateM238animateDpAsStateAjpBEmI2, stateM205animateColorAsStateeuL9pac2, userProfile6, function3, function4, z8, z, mutableState, navController, mutableState2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                }, composer2, 54), composer2, 48);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                z5 = z8;
                userProfile3 = userProfile6;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.stremio.tv.views.menu.NavigationMenuKt$$ExternalSyntheticLambda12
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return NavigationMenuKt.NavigationMenu$lambda$16(navController, userProfile3, z, z5, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 48;
        userProfile2 = userProfile;
        if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            if (composerStartRestartGroup.changed(z)) {
                i6 = 256;
            } else {
                i6 = 128;
            }
            i3 |= i6;
        }
        i4 = i2 & 8;
        if (i4 != 0) {
            if ((i & 3072) == 0) {
                z3 = z2;
                if (composerStartRestartGroup.changed(z3)) {
                    i5 = 2048;
                } else {
                    i5 = 1024;
                }
                i3 |= i5;
            }
            if ((i3 & 1171) != 1170) {
                z4 = true;
            } else {
                z4 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z4, i3 & 1)) {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                userProfile3 = userProfile2;
                z5 = z3;
            } else {
                if (i7 != 0) {
                    userProfile4 = null;
                } else {
                    userProfile4 = userProfile2;
                }
                if (i4 != 0) {
                    z6 = false;
                } else {
                    z6 = z3;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-18498152, i3, -1, "com.stremio.tv.views.menu.NavigationMenu (NavigationMenu.kt:115)");
                }
                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection3 = CompositionLocalsKt.getLocalLayoutDirection();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                Object objConsume3 = composerStartRestartGroup.consume(localLayoutDirection3);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                final LayoutDirection layoutDirection3 = (LayoutDirection) objConsume3;
                Object[] objArr7 = new Object[0];
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -596846858, "CC(remember):NavigationMenu.kt#9igjgp");
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = new Function0() { // from class: com.stremio.tv.views.menu.NavigationMenuKt$$ExternalSyntheticLambda4
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return NavigationMenuKt.NavigationMenu$lambda$0$0();
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                mutableState = (MutableState) RememberSaveableKt.rememberSaveable(objArr7, (Function0) objRememberedValue, composerStartRestartGroup, 48);
                Object[] objArr8 = new Object[0];
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -596844708, "CC(remember):NavigationMenu.kt#9igjgp");
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue2 = new Function0() { // from class: com.stremio.tv.views.menu.NavigationMenuKt$$ExternalSyntheticLambda5
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return NavigationMenuKt.NavigationMenu$lambda$3$0();
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                mutableState2 = (MutableState) RememberSaveableKt.rememberSaveable(objArr8, (Function0) objRememberedValue2, composerStartRestartGroup, 48);
                Object[] objArr9 = new Object[0];
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -596842351, "CC(remember):NavigationMenu.kt#9igjgp");
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue3 = new Function0() { // from class: com.stremio.tv.views.menu.NavigationMenuKt$$ExternalSyntheticLambda6
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return NavigationMenuKt.NavigationMenu$lambda$6$0();
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                final MutableState mutableState5 = (MutableState) RememberSaveableKt.rememberSaveable(objArr9, (Function0) objRememberedValue3, composerStartRestartGroup, 48);
                Integer numNavigationMenu$lambda$3 = NavigationMenu$lambda$1(mutableState);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -596840880, "CC(remember):NavigationMenu.kt#9igjgp");
                zChanged = composerStartRestartGroup.changed(numNavigationMenu$lambda$3);
                objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                if (!zChanged) {
                    objRememberedValue4 = Boolean.valueOf(CollectionsKt.contains(VISIBLE_ROUTES, NavigationMenu$lambda$1(mutableState)));
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                } else {
                    objRememberedValue4 = Boolean.valueOf(CollectionsKt.contains(VISIBLE_ROUTES, NavigationMenu$lambda$1(mutableState)));
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                }
                boolean zBooleanValue3 = ((Boolean) objRememberedValue4).booleanValue();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                zNavigationMenu$lambda$7 = NavigationMenu$lambda$7(mutableState5);
                if (zNavigationMenu$lambda$7) {
                    fM11528getNavBarWidthD9Ej5fM = Dp.m8482constructorimpl(185.0f);
                } else {
                    if (!zNavigationMenu$lambda$7) {
                        throw new NoWhenBranchMatchedException();
                    }
                    fM11528getNavBarWidthD9Ej5fM = Sizes.INSTANCE.m11528getNavBarWidthD9Ej5fM();
                }
                final UserProfile userProfile7 = userProfile4;
                final State stateM238animateDpAsStateAjpBEmI3 = AnimateAsStateKt.m238animateDpAsStateAjpBEmI(fM11528getNavBarWidthD9Ej5fM, null, null, null, composerStartRestartGroup, 0, 14);
                zNavigationMenu$lambda$8 = NavigationMenu$lambda$7(mutableState5);
                if (zNavigationMenu$lambda$8) {
                    jM5603getTransparent0d7_KjU = Colors.INSTANCE.m11514getPrimaryBackground0d7_KjU();
                } else {
                    if (!zNavigationMenu$lambda$8) {
                        throw new NoWhenBranchMatchedException();
                    }
                    jM5603getTransparent0d7_KjU = Color.INSTANCE.m5603getTransparent0d7_KjU();
                }
                final State stateM205animateColorAsStateeuL9pac3 = SingleValueAnimationKt.m205animateColorAsStateeuL9pac(jM5603getTransparent0d7_KjU, null, null, null, composerStartRestartGroup, 0, 14);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -596827308, "CC(remember):NavigationMenu.kt#9igjgp");
                zChanged2 = composerStartRestartGroup.changed(mutableState2);
                objRememberedValue5 = composerStartRestartGroup.rememberedValue();
                if (!zChanged2) {
                    objRememberedValue5 = new Function1() { // from class: com.stremio.tv.views.menu.NavigationMenuKt$$ExternalSyntheticLambda7
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return NavigationMenuKt.NavigationMenu$lambda$12$0(mutableState2, ((Integer) obj).intValue());
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                } else {
                    objRememberedValue5 = new Function1() { // from class: com.stremio.tv.views.menu.NavigationMenuKt$$ExternalSyntheticLambda7
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return NavigationMenuKt.NavigationMenu$lambda$12$0(mutableState2, ((Integer) obj).intValue());
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                }
                final Function1 function5 = (Function1) objRememberedValue5;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -596824977, "CC(remember):NavigationMenu.kt#9igjgp");
                zChanged3 = composerStartRestartGroup.changed(mutableState) | composerStartRestartGroup.changedInstance(navController);
                objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                if (!zChanged3) {
                    objRememberedValue6 = new Function1() { // from class: com.stremio.tv.views.menu.NavigationMenuKt$$ExternalSyntheticLambda8
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return NavigationMenuKt.NavigationMenu$lambda$13$0(navController, mutableState, ((Integer) obj).intValue());
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
                } else {
                    objRememberedValue6 = new Function1() { // from class: com.stremio.tv.views.menu.NavigationMenuKt$$ExternalSyntheticLambda8
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return NavigationMenuKt.NavigationMenu$lambda$13$0(navController, mutableState, ((Integer) obj).intValue());
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
                }
                final Function1 function6 = (Function1) objRememberedValue6;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Unit unit3 = Unit.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -596816962, "CC(remember):NavigationMenu.kt#9igjgp");
                zChanged4 = composerStartRestartGroup.changed(mutableState) | composerStartRestartGroup.changed(mutableState2) | composerStartRestartGroup.changedInstance(navController);
                objRememberedValue7 = composerStartRestartGroup.rememberedValue();
                if (!zChanged4) {
                    objRememberedValue7 = new Function1() { // from class: com.stremio.tv.views.menu.NavigationMenuKt$$ExternalSyntheticLambda9
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return NavigationMenuKt.NavigationMenu$lambda$14$0(navController, mutableState, mutableState2, (DisposableEffectScope) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue7);
                } else {
                    objRememberedValue7 = new Function1() { // from class: com.stremio.tv.views.menu.NavigationMenuKt$$ExternalSyntheticLambda9
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return NavigationMenuKt.NavigationMenu$lambda$14$0(navController, mutableState, mutableState2, (DisposableEffectScope) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue7);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                EffectsKt.DisposableEffect(unit3, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) objRememberedValue7, composerStartRestartGroup, 6);
                composer2 = composerStartRestartGroup;
                final boolean z9 = z6;
                FadeInOutKt.FadeInOut(zBooleanValue3, ComposableLambdaKt.rememberComposableLambda(-28920841, true, new Function2() { // from class: com.stremio.tv.views.menu.NavigationMenuKt$$ExternalSyntheticLambda10
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return NavigationMenuKt.NavigationMenu$lambda$15(layoutDirection3, mutableState5, stateM238animateDpAsStateAjpBEmI3, stateM205animateColorAsStateeuL9pac3, userProfile7, function5, function6, z9, z, mutableState, navController, mutableState2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                }, composer2, 54), composer2, 48);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                z5 = z9;
                userProfile3 = userProfile7;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.stremio.tv.views.menu.NavigationMenuKt$$ExternalSyntheticLambda12
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return NavigationMenuKt.NavigationMenu$lambda$16(navController, userProfile3, z, z5, i, i2, (Composer) obj, ((In

... [truncated: exceeds 60000 bytes, total 148276 bytes]