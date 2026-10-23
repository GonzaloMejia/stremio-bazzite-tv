package com.stremio.tv.views.search;

import android.net.Uri;
import androidx.activity.compose.BackHandlerKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.lazy.LazyDslKt;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.foundation.lazy.LazyListScope;
import androidx.compose.foundation.style.StylePropertiesKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
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
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusDirection;
import androidx.compose.ui.focus.FocusManager;
import androidx.compose.ui.input.key.Key;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.unit.Dp;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.navigation.NavController;
import com.google.android.gms.actions.SearchIntents;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.stremio.common.composables.IconKt;
import com.stremio.common.composables.SearchFieldKt;
import com.stremio.common.extensions.ModifierExtKt;
import com.stremio.common.extensions.NavigationExtKt;
import com.stremio.common.translations.StringsKt;
import com.stremio.common.viewmodels.MetaPreviewViewModel;
import com.stremio.common.viewmodels.SearchViewModel;
import com.stremio.common.viewmodels.state.MetaPreviewEvent;
import com.stremio.common.viewmodels.state.MetaRow;
import com.stremio.core.Field;
import com.stremio.core.models.Catalog;
import com.stremio.core.types.resource.MetaItemPreview;
import com.stremio.icons.Icons;
import com.stremio.translations.Strings;
import com.stremio.tv.Colors;
import com.stremio.tv.FontSizes;
import com.stremio.tv.ThemeKt;
import com.stremio.tv.composables.ButtonKt;
import com.stremio.tv.composables.CatalogRowsKt;
import com.stremio.tv.composables.LabelKt;
import com.stremio.tv.views.metapreview.MetaPreviewKt;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.koin.compose.KoinApplicationKt;
import org.koin.viewmodel.CreationExtrasExtKt;
import org.koin.viewmodel.GetViewModelKt;

/* JADX INFO: compiled from: SearchPage.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000V\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u001a/\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0007b\u0002\b\fb\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f¢\u0006\u0002\u0010\u000b\u001aO\u0010\u0010\u001a\u00020\u00062\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00060\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00060\u0017H\u0003b\u0002\b\fb\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f¢\u0006\u0002\u0010\u0018\"\u0013\u0010\u0000\u001a\u00020\u0001¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b\u0002\u0010\u0003¨\u0006\u0019²\u0006\u0010\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\u0012X\u008a\u0084\u0002²\u0006\u0010\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012X\u008a\u0084\u0002²\u0006\u0010\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012X\u008a\u0084\u0002²\u0006\u0010\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\u0012X\u008a\u0084\u0002²\u0006\n\u0010 \u001a\u00020\u0013X\u008a\u008e\u0002²\u0006\n\u0010!\u001a\u00020\"X\u008a\u008e\u0002²\u0006\n\u0010#\u001a\u00020\"X\u008a\u008e\u0002"}, d2 = {"START_PADDING", "Landroidx/compose/ui/unit/Dp;", "getSTART_PADDING", "()F", "F", "SearchPage", "", "args", "Lcom/stremio/tv/views/search/SearchFragmentArgs;", "navController", "Landroidx/navigation/NavController;", "(Lcom/stremio/tv/views/search/SearchFragmentArgs;Landroidx/navigation/NavController;Landroidx/compose/runtime/Composer;I)V", "Landroidx/compose/runtime/Composable;", "Landroidx/compose/runtime/ComposableTarget;", "applier", "androidx.compose.ui.UiComposable", "History", FirebaseAnalytics.Param.ITEMS, "", "", "onSubmit", "Lkotlin/Function1;", "onClear", "Lkotlin/Function0;", "(Ljava/util/List;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "Stremio:androidTV-com.stremio.one-1.11.2-40000002_release", "searchState", "Lcom/stremio/core/models/Catalog;", "history", "suggestions", "catalogRows", "Lcom/stremio/common/viewmodels/state/MetaRow$CatalogRow;", SearchIntents.EXTRA_QUERY, "searchBarVisible", "", "firstRowFocused"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class SearchPageKt {
    private static final float START_PADDING = Dp.m8482constructorimpl(35.0f);

    static final Unit History$lambda$2(List list, Function1 function1, Function0 function0, int i, Composer composer, int i2) {
        History(list, function1, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit SearchPage$lambda$22(SearchFragmentArgs searchFragmentArgs, NavController navController, int i, Composer composer, int i2) {
        SearchPage(searchFragmentArgs, navController, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static final float getSTART_PADDING() {
        return START_PADDING;
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
    public static final void SearchPage(final SearchFragmentArgs args, final NavController navController, Composer composer, final int i) {
        int i2;
        String str;
        MutableState mutableState;
        String str2;
        String str3;
        int i3;
        Function1 function1;
        Intrinsics.checkNotNullParameter(args, "args");
        Intrinsics.checkNotNullParameter(navController, "navController");
        Composer composerStartRestartGroup = composer.startRestartGroup(1337466644);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(SearchPage)N(args,navController)59@2430L7,61@2492L15,62@2551L15,63@2612L16,64@2672L16,65@2740L27,67@2792L229,78@3057L30,78@3040L47,79@3133L24,79@3116L41,80@3202L25,80@3185L42,84@3316L201,95@3560L135,103@3727L46,107@3811L41,111@3904L98,115@4053L111,121@4201L39,121@4170L70,125@4246L2144:SearchPage.kt#56l3y0");
        if ((i & 6) == 0) {
            i2 = i | ((i & 8) == 0 ? composerStartRestartGroup.changed(args) : composerStartRestartGroup.changedInstance(args) ? 4 : 2);
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(navController) ? 32 : 16;
        }
        int i4 = i2;
        if (!composerStartRestartGroup.shouldExecute((i4 & 19) != 18, i4 & 1)) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1337466644, i4, -1, "com.stremio.tv.views.search.SearchPage (SearchPage.kt:58)");
            }
            ProvidableCompositionLocal<FocusManager> localFocusManager = CompositionLocalsKt.getLocalFocusManager();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = composerStartRestartGroup.consume(localFocusManager);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final FocusManager focusManager = (FocusManager) objConsume;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -924953623, "CC(koinViewModel)N(qualifier,viewModelStoreOwner,key,extras,scope,parameters)48@1587L7,51@1782L18:ViewModel.kt#7bazx");
            ViewModelStoreOwner current = LocalViewModelStoreOwner.INSTANCE.getCurrent(composerStartRestartGroup, LocalViewModelStoreOwner.$stable);
            if (current == null) {
                throw new IllegalStateException("No ViewModelStoreOwner was provided via LocalViewModelStoreOwner".toString());
            }
            ViewModel viewModelResolveViewModel = GetViewModelKt.resolveViewModel(Reflection.getOrCreateKotlinClass(MetaPreviewViewModel.class), current.getViewModelStore(), null, CreationExtrasExtKt.defaultExtras(current), null, KoinApplicationKt.currentKoinScope(composerStartRestartGroup, 0), null);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final MetaPreviewViewModel metaPreviewViewModel = (MetaPreviewViewModel) viewModelResolveViewModel;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -924953623, "CC(koinViewModel)N(qualifier,viewModelStoreOwner,key,extras,scope,parameters)48@1587L7,51@1782L18:ViewModel.kt#7bazx");
            ViewModelStoreOwner current2 = LocalViewModelStoreOwner.INSTANCE.getCurrent(composerStartRestartGroup, LocalViewModelStoreOwner.$stable);
            if (current2 == null) {
                throw new IllegalStateException("No ViewModelStoreOwner was provided via LocalViewModelStoreOwner".toString());
            }
            ViewModel viewModelResolveViewModel2 = GetViewModelKt.resolveViewModel(Reflection.getOrCreateKotlinClass(SearchViewModel.class), current2.getViewModelStore(), null, CreationExtrasExtKt.defaultExtras(current2), null, KoinApplicationKt.currentKoinScope(composerStartRestartGroup, 0), null);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final SearchViewModel searchViewModel = (SearchViewModel) viewModelResolveViewModel2;
            final State stateCollectAsState = SnapshotStateKt.collectAsState(searchViewModel.getState(), null, composerStartRestartGroup, 0, 1);
            State stateCollectAsState2 = SnapshotStateKt.collectAsState(searchViewModel.getHistory(), null, composerStartRestartGroup, 0, 1);
            State stateCollectAsState3 = SnapshotStateKt.collectAsState(searchViewModel.getSuggestions(), CollectionsKt.emptyList(), null, composerStartRestartGroup, 48, 2);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1110239193, "CC(remember):SearchPage.kt#9igjgp");
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = SnapshotStateKt.derivedStateOf(new Function0() { // from class: com.stremio.tv.views.search.SearchPageKt$$ExternalSyntheticLambda8
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return SearchPageKt.SearchPage$lambda$3$0(stateCollectAsState);
                    }
                });
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            State state = (State) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Object[] objArr = new Object[0];
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1110247474, "CC(remember):SearchPage.kt#9igjgp");
            boolean z = (i4 & 14) == 4 || ((i4 & 8) != 0 && composerStartRestartGroup.changedInstance(args));
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (z || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = new Function0() { // from class: com.stremio.tv.views.search.SearchPageKt$$ExternalSyntheticLambda13
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return SearchPageKt.SearchPage$lambda$5$0(args);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final MutableState mutableState2 = (MutableState) RememberSaveableKt.rememberSaveable(objArr, (Function0) objRememberedValue2, composerStartRestartGroup, 0);
            Object[] objArr2 = new Object[0];
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1110249900, "CC(remember):SearchPage.kt#9igjgp");
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue3 = new Function0() { // from class: com.stremio.tv.views.search.SearchPageKt$$ExternalSyntheticLambda14
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return SearchPageKt.SearchPage$lambda$8$0();
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final MutableState mutableState3 = (MutableState) RememberSaveableKt.rememberSaveable(objArr2, (Function0) objRememberedValue3, composerStartRestartGroup, 48);
            Object[] objArr3 = new Object[0];
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1110252109, "CC(remember):SearchPage.kt#9igjgp");
            Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue4 = new Function0() { // from class: com.stremio.tv.views.search.SearchPageKt$$ExternalSyntheticLambda15
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return SearchPageKt.SearchPage$lambda$11$0();
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final MutableState mutableState4 = (MutableState) RememberSaveableKt.rememberSaveable(objArr3, (Function0) objRememberedValue4, composerStartRestartGroup, 48);
            boolean z2 = SearchPage$lambda$6(mutableState2).length() > 0;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1110255933, "CC(remember):SearchPage.kt#9igjgp");
            boolean zChanged = composerStartRestartGroup.changed(mutableState3) | composerStartRestartGroup.changed(mutableState2) | composerStartRestartGroup.changedInstance(searchViewModel);
            Object objRememberedValue5 = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue5 = new Function1() { // from class: com.stremio.tv.views.search.SearchPageKt$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return SearchPageKt.SearchPage$lambda$14$0(searchViewModel, mutableState3, mutableState2, (String) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
            }
            Function1 function2 = (Function1) objRememberedValue5;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1110263675, "CC(remember):SearchPage.kt#9igjgp");
            boolean zChanged2 = composerStartRestartGroup.changed(mutableState2) | composerStartRestartGroup.changedInstance(searchViewModel);
            Object objRememberedValue6 = composerStartRestartGroup.rememberedValue();
            if (zChanged2 || objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue6 = new Function1() { // from class: com.stremio.tv.views.search.SearchPageKt$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return SearchPageKt.SearchPage$lambda$15$0(searchViewModel, mutableState2, (String) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
            }
            Function1 function3 = (Function1) objRememberedValue6;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1110268930, "CC(remember):SearchPage.kt#9igjgp");
            boolean zChangedInstance = composerStartRestartGroup.changedInstance(searchViewModel);
            boolean z3 = z2;
            Object objRememberedValue7 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance || objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue7 = new Function0() { // from class: com.stremio.tv.views.search.SearchPageKt$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return SearchPageKt.SearchPage$lambda$16$0(searchViewModel);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue7);
            }
            Function0 function0 = (Function0) objRememberedValue7;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1110271613, "CC(remember):SearchPage.kt#9igjgp");
            boolean zChanged3 = composerStartRestartGroup.changed(mutableState4);
            Object objRememberedValue8 = composerStartRestartGroup.rememberedValue();
            if (zChanged3 || objRememberedValue8 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue8 = new Function1() { // from class: com.stremio.tv.views.search.SearchPageKt$$ExternalSyntheticLambda4
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return SearchPageKt.SearchPage$lambda$17$0(mutableState4, ((Integer) obj).intValue());
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue8);
            }
            Function1 function4 = (Function1) objRememberedValue8;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1110274646, "CC(remember):SearchPage.kt#9igjgp");
            boolean zChangedInstance2 = composerStartRestartGroup.changedInstance(metaPreviewViewModel);
            Object objRememberedValue9 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance2 || objRememberedValue9 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue9 = new Function1() { // from class: com.stremio.tv.views.search.SearchPageKt$$ExternalSyntheticLambda5
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return SearchPageKt.SearchPage$lambda$18$0(metaPreviewViewModel, (MetaItemPreview) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue9);
            }
            Function1 function5 = (Function1) objRememberedValue9;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1110279427, "CC(remember):SearchPage.kt#9igjgp");
            boolean zChangedInstance3 = composerStartRestartGroup.changedInstance(navController);
            Object objRememberedValue10 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance3 || objRememberedValue10 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue10 = new Function1() { // from class: com.stremio.tv.views.search.SearchPageKt$$ExternalSyntheticLambda6
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return SearchPageKt.SearchPage$lambda$19$0(navController, (MetaItemPreview) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue10);
            }
            Function1 function6 = (Function1) objRememberedValue10;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            boolean z4 = !SearchPage$lambda$9(mutableState3);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1110284091, "CC(remember):SearchPage.kt#9igjgp");
            boolean zChanged4 = composerStartRestartGroup.changed(mutableState3);
            Object objRememberedValue11 = composerStartRestartGroup.rememberedValue();
            if (zChanged4 || objRememberedValue11 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue11 = new Function0() { // from class: com.stremio.tv.views.search.SearchPageKt$$ExternalSyntheticLambda9
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return SearchPageKt.SearchPage$lambda$20$0(mutableState3);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue11);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            BackHandlerKt.BackHandler(z4, (Function0) objRememberedValue11, composerStartRestartGroup, 0, 0);
            Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierFillMaxSize$default);
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
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1335896932, "C:SearchPage.kt#56l3y0");
            if (z3) {
                composerStartRestartGroup.startReplaceGroup(1335877556);
                ComposerKt.sourceInformation(composerStartRestartGroup, "129@4333L166,138@4672L161,135@4513L616");
                Modifier.Companion companion = Modifier.INSTANCE;
                float f = START_PADDING;
                str = "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo";
                function1 = function2;
                MetaPreviewKt.MetaPreview(PaddingKt.m1121paddingqDBjuR0$default(companion, f, 0.0f, 0.0f, 0.0f, 14, null), metaPreviewViewModel, SearchPage$lambda$9(mutableState3), composerStartRestartGroup, (MetaPreviewViewModel.$stable << 3) | 6, 0);
                Modifier modifierM1121paddingqDBjuR0$default = PaddingKt.m1121paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, Dp.m8482constructorimpl(220.0f), Dp.m8482constructorimpl(20.0f), 0.0f, 9, null);
                long jM6530getDirectionUpEK5gGoQ = Key.INSTANCE.m6530getDirectionUpEK5gGoQ();
                boolean zSearchPage$lambda$12 = SearchPage$lambda$12(mutableState4);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1844218779, "CC(remember):SearchPage.kt#9igjgp");
                boolean zChangedInstance4 = composerStartRestartGroup.changedInstance(focusManager) | composerStartRestartGroup.changed(mutableState3);
                Object objRememberedValue12 = composerStartRestartGroup.rememberedValue();
                if (zChangedInstance4) {
                    mutableState = mutableState2;
                } else {
                    mutableState = mutableState2;
                    if (objRememberedValue12 == Composer.INSTANCE.getEmpty()) {
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    str2 = "C72@3469L9:Box.kt#2w3rfo";
                    str3 = "CC(remember):SearchPage.kt#9igjgp";
                    i3 = -1159599143;
                    CatalogRowsKt.CatalogRows(ModifierExtKt.m11491onKeyDownS8TFbT0(modifierM1121paddingqDBjuR0$default, jM6530getDirectionUpEK5gGoQ, zSearchPage$lambda$12, (Function0) objRememberedValue12), PaddingKt.m1114PaddingValuesa9UjIt4$default(f, 0.0f, 0.0f, 0.0f, 14, null), Field.SEARCH.INSTANCE, SearchPage$lambda$4(state), function4, function5, function6, composerStartRestartGroup, 432, 0);
                    composerStartRestartGroup = composerStartRestartGroup;
                    composerStartRestartGroup.endReplaceGroup();
                }
                objRememberedValue12 = new Function0() { // from class: com.stremio.tv.views.search.SearchPageKt$$ExternalSyntheticLambda10
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return SearchPageKt.SearchPage$lambda$21$0$0(focusManager, mutableState3);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue12);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                str2 = "C72@3469L9:Box.kt#2w3rfo";
                str3 = "CC(remember):SearchPage.kt#9igjgp";
                i3 = -1159599143;
                CatalogRowsKt.CatalogRows(ModifierExtKt.m11491onKeyDownS8TFbT0(modifierM1121paddingqDBjuR0$default, jM6530getDirectionUpEK5gGoQ, zSearchPage$lambda$12, (Function0) objRememberedValue12), PaddingKt.m1114PaddingValuesa9UjIt4$default(f, 0.0f, 0.0f, 0.0f, 14, null), Field.SEARCH.INSTANCE, SearchPage$lambda$4(state), function4, function5, function6, composerStartRestartGroup, 432, 0);
                composerStartRestartGroup = composerStartRestartGroup;
                composerStartRestartGroup.endReplaceGroup();
            } else {
                str = "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo";
                mutableState = mutableState2;
                str2 = "C72@3469L9:Box.kt#2w3rfo";
                str3 = "CC(remember):SearchPage.kt#9igjgp";
                i3 = -1159599143;
                function1 = function2;
                composerStartRestartGroup.startReplaceGroup(1336680239);
                ComposerKt.sourceInformation(composerStartRestartGroup, "");
                if (!SearchPage$lambda$1(stateCollectAsState2).isEmpty()) {
                    composerStartRestartGroup.startReplaceGroup(1336716788);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "151@5203L148");
                    History(SearchPage$lambda$1(stateCollectAsState2), function1, function0, composerStartRestartGroup, 0);
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    composerStartRestartGroup.startReplaceGroup(1336897115);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "157@5389L13");
                    PlaceholderKt.Placeholder(composerStartRestartGroup, 0);
                    composerStartRestartGroup.endReplaceGroup();
                }
                composerStartRestartGroup.endReplaceGroup();
            }
            if (SearchPage$lambda$9(mutableState3)) {
                composerStartRestartGroup.startReplaceGroup(1337010730);
                ComposerKt.sourceInformation(composerStartRestartGroup, "162@5472L902");
                Modifier modifierM1121paddingqDBjuR0$default2 = PaddingKt.m1121paddingqDBjuR0$default(Modifier.INSTANCE, START_PADDING, Dp.m8482constructorimpl(15.0f), Dp.m8482constructorimpl(20.0f), 0.0f, 8, null);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1042775818, str);
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, i3, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
                int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM1121paddingqDBjuR0$default2);
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
                Updater.m4667setimpl(composerM4659constructorimpl2, measurePolicyMaybeCachedBoxMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4667setimpl(composerM4659constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4667setimpl(composerM4659constructorimpl2, Integer.valueOf(iHashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4665reconcileimpl(composerM4659constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4667setimpl(composerM4659constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1833054614, str2);
                BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1610374278, "C168@5680L11,169@5762L175,175@6053L16,166@5613L747:SearchPage.kt#56l3y0");
                Modifier modifierAutoFocus = ModifierExtKt.autoFocus(Modifier.INSTANCE, false, composerStartRestartGroup, 6, 1);
                long jM6525getDirectionDownEK5gGoQ = Key.INSTANCE.m6525getDirectionDownEK5gGoQ();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -225142911, str3);
                boolean zChangedInstance5 = composerStartRestartGroup.changedInstance(focusManager) | composerStartRestartGroup.changed(mutableState3);
                Object objRememberedValue13 = composerStartRestartGroup.rememberedValue();
                if (zChangedInstance5 || objRememberedValue13 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue13 = new Function0() { // from class: com.stremio.tv.views.search.SearchPageKt$$ExternalSyntheticLambda11
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return SearchPageKt.SearchPage$lambda$21$1$0$0(focusManager, mutableState3);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue13);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Composer composer2 = composerStartRestartGroup;
                SearchFieldKt.m11472SearchFieldTtFQ49E(ModifierExtKt.m11491onKeyDownS8TFbT0(modifierAutoFocus, jM6525getDirectionDownEK5gGoQ, z3, (Function0) objRememberedValue13), SearchPage$lambda$6(mutableState), ThemeKt.labelTextStyle(composerStartRestartGroup, 0), Colors.INSTANCE.m11513getPrimaryAccent0d7_KjU(), Colors.INSTANCE.m11515getPrimaryForeground0d7_KjU(), Colors.INSTANCE.m11520getTertiaryBackground0d7_KjU(), SearchPage$lambda$2(stateCollectAsState3), function3, function1, composer2, 0, 0);
                composerStartRestartGroup = composer2;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composerStartRestartGroup.endNode();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(1337900616);
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.stremio.tv.views.search.SearchPageKt$$ExternalSyntheticLambda12
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SearchPageKt.SearchPage$lambda$22(args, navController, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List SearchPage$lambda$3$0(State state) {
        List<Catalog> listSearchPage$lambda$0 = SearchPage$lambda$0(state);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listSearchPage$lambda$0, 10));
        Iterator<T> it = listSearchPage$lambda$0.iterator();
        while (it.hasNext()) {
            arrayList.add(new MetaRow.CatalogRow((Catalog) it.next(), false, false, false, 6, null));
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MutableState SearchPage$lambda$5$0(SearchFragmentArgs searchFragmentArgs) {
        return SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(searchFragmentArgs.getQuery(), null, 2, null);
    }

    private static final String SearchPage$lambda$6(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final void SearchPage$lambda$10(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MutableState SearchPage$lambda$8$0() {
        return SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(true, null, 2, null);
    }

    private static final boolean SearchPage$lambda$9(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MutableState SearchPage$lambda$11$0() {
        return SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
    }

    private static final boolean SearchPage$lambda$12(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void SearchPage$lambda$13(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SearchPage$lambda$14$0(SearchViewModel searchViewModel, MutableState mutableState, MutableState mutableState2, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        if (it.length() > 0) {
            SearchPage$lambda$10(mutableState, false);
            if (!Intrinsics.areEqual(it, SearchPage$lambda$6(mutableState2))) {
                mutableState2.setValue(it);
                searchViewModel.search(it);
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SearchPage$lambda$15$0(SearchViewModel searchViewModel, MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        if (it.length() != 0) {
            searchViewModel.searchSuggestions(it);
        } else {
            mutableState.setValue(it);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SearchPage$lambda$16$0(SearchViewModel searchViewModel) {
        searchViewModel.clearHistory();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SearchPage$lambda$17$0(MutableState mutableState, int i) {
        SearchPage$lambda$13(mutableState, i == 0);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SearchPage$lambda$18$0(MetaPreviewViewModel metaPreviewViewModel, MetaItemPreview metaItemPreview) {
        metaPreviewViewModel.onEvent(new MetaPreviewEvent.PreviewMetaItem(metaItemPreview, true));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SearchPage$lambda$19$0(NavController navController, MetaItemPreview it) {
        Intrinsics.checkNotNullParameter(it, "it");
        String strDeeplink = NavigationExtKt.deeplink(it);
        if (strDeeplink != null) {
            Uri uri = Uri.parse(strDeeplink);
            Intrinsics.checkNotNullExpressionValue(uri, "parse(...)");
            navController.navigate(uri);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SearchPage$lambda$20$0(MutableState mutableState) {
        SearchPage$lambda$10(mutableState, true);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SearchPage$lambda$21$0$0(FocusManager focusManager, MutableState mutableState) {
        if (!focusManager.mo5182moveFocus3ESFkO8(FocusDirection.INSTANCE.m5179getUpdhqQ8s())) {
            SearchPage$lambda$10(mutableState, true);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SearchPage$lambda$21$1$0$0(FocusManager focusManager, MutableState mutableState) {
        if (focusManager.mo5182moveFocus3ESFkO8(FocusDirection.INSTANCE.m5172getDowndhqQ8s())) {
            SearchPage$lambda$10(mutableState, false);
        }
        return Unit.INSTANCE;
    }

    private static final void History(final List<String> list, final Function1<? super String, Unit> function1, final Function0<Unit> function0, Composer composer, final int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(352975970);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(History)N(items,onSubmit,onClear)193@6544L7,195@6557L1403,240@7966L277:SearchPage.kt#56l3y0");
        if ((i & 6) == 0) {
            i2 = ((i & 8) == 0 ? composerStartRestartGroup.changed(list) : composerStartRestartGroup.changedInstance(list) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function1) ? 32 : 16;
        }
        if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function0) ? 256 : 128;
        }
        if (!composerStartRestartGroup.shouldExecute((i2 & 147) != 146, i2 & 1)) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(352975970, i2, -1, "com.stremio.tv.views.search.History (SearchPage.kt:192)");
            }
            ProvidableCompositionLocal<Strings> localStrings = StringsKt.getLocalStrings();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = composerStartRestartGroup.consume(localStrings);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Strings strings = (Strings) objConsume;
            Modifier modifierM1121paddingqDBjuR0$default = PaddingKt.m1121paddingqDBjuR0$default(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m8482constructorimpl(35.0f), Dp.m8482constructorimpl(90.0f), Dp.m8482constructorimpl(35.0f), 0.0f, 8, null);
            Arrangement.HorizontalOrVertical spaceBetween = Arrangement.INSTANCE.getSpaceBetween();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(spaceBetween, Alignment.INSTANCE.getStart(), composerStartRestartGroup, 6);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM1121paddingqDBjuR0$default);
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
            Updater.m4667setimpl(composerM4659constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4667setimpl(composerM4659constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4667setimpl(composerM4659constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4665reconcileimpl(composerM4659constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4667setimpl(composerM4659constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1911299427, "C201@6744L1210:SearchPage.kt#56l3y0");
            Modifier modifierWeight$default = ColumnScope.weight$default(columnScopeInstance, Modifier.INSTANCE, 1.0f, false, 2, null);
            Arrangement.HorizontalOrVertical horizontalOrVerticalM809spacedBy0680j_4 = Arrangement.INSTANCE.m809spacedBy0680j_4(Dp.m8482constructorimpl(10.0f));
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy2 = ColumnKt.columnMeasurePolicy(horizontalOrVerticalM809spacedBy0680j_4, Alignment.INSTANCE.getStart(), composerStartRestartGroup, 6);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
            CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierWeight$default);
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
            Updater.m4667setimpl(composerM4659constructorimpl2, measurePolicyColumnMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4667setimpl(composerM4659constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4667setimpl(composerM4659constructorimpl2, Integer.valueOf(iHashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4665reconcileimpl(composerM4659constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4667setimpl(composerM4659constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance2 = ColumnScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -517960867, "C205@6872L546,225@7601L343,222@7432L512:SearchPage.kt#56l3y0");
            Modifier modifierM1121paddingqDBjuR0$default2 = PaddingKt.m1121paddingqDBjuR0$default(Modifier.INSTANCE, Dp.m8482constructorimpl(5.0f), 0.0f, 0.0f, 0.0f, 14, null);
            Arrangement.HorizontalOrVertical horizontalOrVerticalM809spacedBy0680j_5 = Arrangement.INSTANCE.m809spacedBy0680j_4(Dp.m8482constructorimpl(7.0f));
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(horizontalOrVerticalM809spacedBy0680j_5, centerVertically, composerStartRestartGroup, 54);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode3 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
            CompositionLocalMap currentCompositionLocalMap3 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM1121paddingqDBjuR0$default2);
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
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -765060970, "C210@7089L120,215@7227L177:SearchPage.kt#56l3y0");
            IconKt.m11460Iconcf5BqRc(Icons.INSTANCE.getClock(), SizeKt.m1184size3ABfNKs(Modifier.INSTANCE, Dp.m8482constructorimpl(18.0f)), 0L, null, composerStartRestartGroup, 48, 12);
            LabelKt.m11539Label2Q0j3Ts(null, strings.getLabel_history(), FontSizes.INSTANCE.m11523getMediumXSAIIZE(), FontWeight.INSTANCE.getSemiBold(), 0L, 0, composerStartRestartGroup, 3072, 49);
            composerStartRestartGroup = composerStartRestartGroup;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composerStartRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Arrangement.HorizontalOrVertical horizontalOrVerticalM809spacedBy0680j_6 = Arrangement.INSTANCE.m809spacedBy0680j_4(Dp.m8482constructorimpl(10.0f));
            PaddingValues paddingValuesM1114PaddingValuesa9UjIt4$default = PaddingKt.m1114PaddingValuesa9UjIt4$default(0.0f, Dp.m8482constructorimpl(5.0f), 0.0f, Dp.m8482constructorimpl(20.0f), 5, null);
            Arrangement.HorizontalOrVertical horizontalOrVertical = horizontalOrVerticalM809spacedBy0680j_6;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1230240173, "CC(remember):SearchPage.kt#9igjgp");
            boolean z = ((i2 & StylePropertiesKt.TextDirectionMask) == 32) | ((i2 & 14) == 4 || ((i2 & 8) != 0 && composerStartRestartGroup.changedInstance(list)));
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (z || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function1() { // from class: com.stremio.tv.views.search.SearchPageKt$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return SearchPageKt.History$lambda$0$0$1$0(list, function1, (LazyListScope) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            LazyDslKt.LazyColumn(null, null, paddingValuesM1114PaddingValuesa9UjIt4$default, false, horizontalOrVertical, null, null, false, null, (Function1) objRememberedValue, composerStartRestartGroup, 24960, 491);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composerStartRestar

... [truncated: exceeds 60000 bytes, total 70489 bytes]