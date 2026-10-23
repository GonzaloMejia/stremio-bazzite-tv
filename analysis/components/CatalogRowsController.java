package com.stremio.tv.views.metarow;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.leanback.widget.ListRowPresenter;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleKt;
import com.stremio.common.extensions.CatalogExtKt;
import com.stremio.common.extensions.LoadableExtKt;
import com.stremio.common.viewmodels.PreferencesViewModel;
import com.stremio.common.viewmodels.state.MetaRow;
import com.stremio.core.models.LoadablePage;
import com.stremio.core.models.common.Error;
import com.stremio.core.types.addon.ResourcePath;
import com.stremio.core.types.addon.ResourceRequest;
import com.stremio.core.types.library.LibraryItem;
import com.stremio.core.types.resource.MetaItemPreview;
import com.stremio.core.types.resource.PosterShape;
import com.stremio.tv.providers.StringsProvider;
import com.stremio.tv.views.metarow.model.CatalogErrorModel;
import com.stremio.tv.views.metarow.model.CatalogLoadingModel;
import com.stremio.tv.views.metarow.model.ContinueWatchingItemModel;
import com.stremio.tv.views.metarow.model.LibraryItemModel;
import com.stremio.tv.views.metarow.model.MetaItemModel;
import com.stremio.tv.widget.FixedStartListRowPresenter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import jp.co.cyberagent.lounge.KeyUtilsKt;
import jp.co.cyberagent.lounge.ListRowModel;
import jp.co.cyberagent.lounge.ListRowModelKt;
import jp.co.cyberagent.lounge.LoungeController;
import jp.co.cyberagent.lounge.LoungeControllerPropertyKt;
import jp.co.cyberagent.lounge.LoungeModel;
import jp.co.cyberagent.lounge.SimpleLoungeModelAwaitInterceptor;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

/* JADX INFO: compiled from: CatalogRowsController.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010%\n\u0002\u0010\t\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\u001c\u001a\u00020\u001dH\u0094@¢\u0006\u0002\u0010\u001eJ\u0010\u0010\u001f\u001a\u0004\u0018\u00010\n2\u0006\u0010 \u001a\u00020\u0014J\u001e\u0010!\u001a\u00020\u001d2\u0006\u0010 \u001a\u00020\"2\u0006\u0010#\u001a\u00020$H\u0082@¢\u0006\u0002\u0010%J\u001e\u0010&\u001a\u00020\u001d2\u0006\u0010 \u001a\u00020\"2\u0006\u0010#\u001a\u00020'H\u0082@¢\u0006\u0002\u0010(J\u001e\u0010)\u001a\u00020\u001d2\u0006\u0010 \u001a\u00020\"2\u0006\u0010#\u001a\u00020*H\u0082@¢\u0006\u0002\u0010+J\u0010\u0010,\u001a\u00020\"2\u0006\u0010#\u001a\u00020\nH\u0002J\b\u0010-\u001a\u00020\u001dH\u0016R7\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\n0\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R+\u0010\u0016\u001a\u00020\u00152\u0006\u0010\b\u001a\u00020\u00158B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u001b\u0010\u0011\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aÊ\u0001\f\b/\u0012\b\b0\u0012\u0004\b\u0003\u0010\u0000¨\u0006."}, d2 = {"Lcom/stremio/tv/views/metarow/CatalogRowsController;", "Ljp/co/cyberagent/lounge/LoungeController;", "lifecycle", "Landroidx/lifecycle/Lifecycle;", "preferencesViewModel", "Lcom/stremio/common/viewmodels/PreferencesViewModel;", "<init>", "(Landroidx/lifecycle/Lifecycle;Lcom/stremio/common/viewmodels/PreferencesViewModel;)V", "<set-?>", "", "Lcom/stremio/common/viewmodels/state/MetaRow;", "rows", "getRows", "()Ljava/util/List;", "setRows", "(Ljava/util/List;)V", "rows$delegate", "Lkotlin/properties/ReadWriteProperty;", "keyToMetaRow", "", "", "", "showTitle", "getShowTitle", "()Z", "setShowTitle", "(Z)V", "showTitle$delegate", "buildModels", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "metaRowFromKey", "key", "buildContinueWatchingRow", "", "row", "Lcom/stremio/common/viewmodels/state/MetaRow$ContinueWatchingRow;", "(Ljava/lang/Object;Lcom/stremio/common/viewmodels/state/MetaRow$ContinueWatchingRow;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "buildLibraryRow", "Lcom/stremio/common/viewmodels/state/MetaRow$LibraryRow;", "(Ljava/lang/Object;Lcom/stremio/common/viewmodels/state/MetaRow$LibraryRow;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "buildCatalogRow", "Lcom/stremio/common/viewmodels/state/MetaRow$CatalogRow;", "(Ljava/lang/Object;Lcom/stremio/common/viewmodels/state/MetaRow$CatalogRow;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "metaRowKey", "close", "Stremio:androidTV-com.stremio.one-1.11.2-40000002_release", "Landroidx/compose/runtime/internal/StabilityInferred;", "parameters"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class CatalogRowsController extends LoungeController {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new MutablePropertyReference1Impl(CatalogRowsController.class, "rows", "getRows()Ljava/util/List;", 0), new MutablePropertyReference1Impl(CatalogRowsController.class, "showTitle", "getShowTitle()Z", 0)};
    public static final int $stable = 8;
    private final Map<Long, MetaRow> keyToMetaRow;

    /* JADX INFO: renamed from: rows$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty rows;

    /* JADX INFO: renamed from: showTitle$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty showTitle;

    /* JADX INFO: renamed from: com.stremio.tv.views.metarow.CatalogRowsController$buildModels$1, reason: invalid class name */
    /* JADX INFO: compiled from: CatalogRowsController.kt */
    @Metadata(k = 3, mv = {2, 4, 0}, xi = 560)
    @DebugMetadata(c = "com.stremio.tv.views.metarow.CatalogRowsController", f = "CatalogRowsController.kt", i = {0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2}, l = {64, 65, ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT}, m = "buildModels", n = {"$this$forEach$iv", "element$iv", "key", "row", "$this$forEach$iv", "element$iv", "key", "row", "$this$forEach$iv", "element$iv", "key", "row"}, nl = {65, ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT, 63}, s = {"L$0", "L$2", "L$3", "L$4", "L$0", "L$2", "L$3", "L$4", "L$0", "L$2", "L$3", "L$4"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CatalogRowsController.this.buildModels(this);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public CatalogRowsController(Lifecycle lifecycle, PreferencesViewModel preferencesViewModel) {
        super(lifecycle, null, 2, 0 == true ? 1 : 0);
        Intrinsics.checkNotNullParameter(lifecycle, "lifecycle");
        Intrinsics.checkNotNullParameter(preferencesViewModel, "preferencesViewModel");
        this.rows = LoungeControllerPropertyKt.loungeProp$default(CollectionsKt.emptyList(), null, 2, null);
        this.keyToMetaRow = new LinkedHashMap();
        this.showTitle = LoungeControllerPropertyKt.loungeProp$default(Boolean.valueOf(preferencesViewModel.getState().getValue().getShowCatalogItemTitles()), null, 2, null);
        FixedStartListRowPresenter fixedStartListRowPresenter = new FixedStartListRowPresenter(2, false, 2, null);
        fixedStartListRowPresenter.setShadowEnabled(false);
        fixedStartListRowPresenter.setHeaderPresenter(new CatalogRowsHeader());
        ListRowModel.INSTANCE.setDefaultListRowPresenter(fixedStartListRowPresenter);
        addInterceptor(new SimpleLoungeModelAwaitInterceptor(0, 1, 0 == true ? 1 : 0));
        BuildersKt__Builders_commonKt.launch$default(LifecycleKt.getCoroutineScope(lifecycle), null, null, new AnonymousClass1(preferencesViewModel, this, null), 3, null);
    }

    public final List<MetaRow> getRows() {
        return (List) this.rows.getValue(this, $$delegatedProperties[0]);
    }

    public final void setRows(List<? extends MetaRow> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.rows.setValue(this, $$delegatedProperties[0], list);
    }

    private final boolean getShowTitle() {
        return ((Boolean) this.showTitle.getValue(this, $$delegatedProperties[1])).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setShowTitle(boolean z) {
        this.showTitle.setValue(this, $$delegatedProperties[1], Boolean.valueOf(z));
    }

    /* JADX INFO: renamed from: com.stremio.tv.views.metarow.CatalogRowsController$1, reason: invalid class name */
    /* JADX INFO: compiled from: CatalogRowsController.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 4, 0}, xi = 1328)
    @DebugMetadata(c = "com.stremio.tv.views.metarow.CatalogRowsController$1", f = "CatalogRowsController.kt", i = {}, l = {52}, m = "invokeSuspend", n = {}, nl = {53}, s = {}, v = 2)
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ PreferencesViewModel $preferencesViewModel;
        int label;
        final /* synthetic */ CatalogRowsController this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(PreferencesViewModel preferencesViewModel, CatalogRowsController catalogRowsController, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$preferencesViewModel = preferencesViewModel;
            this.this$0 = catalogRowsController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$preferencesViewModel, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Flow flowDistinctUntilChanged = FlowKt.distinctUntilChanged(new CatalogRowsController$1$invokeSuspend$$inlined$map$1(this.$preferencesViewModel.getState()));
                final CatalogRowsController catalogRowsController = this.this$0;
                this.label = 1;
                if (flowDistinctUntilChanged.collect(new FlowCollector() { // from class: com.stremio.tv.views.metarow.CatalogRowsController.1.2
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                        return emit(((Boolean) obj2).booleanValue(), (Continuation<? super Unit>) continuation);
                    }

                    public final Object emit(boolean z, Continuation<? super Unit> continuation) {
                        catalogRowsController.setShowTitle(z);
                        return Unit.INSTANCE;
                    }
                }, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    @Override // jp.co.cyberagent.lounge.LoungeController
    protected Object buildModels(Continuation<? super Unit> continuation) {
        AnonymousClass1 anonymousClass1;
        ArrayList arrayList;
        Iterator it;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = new AnonymousClass1(continuation);
        }
        Object obj = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = anonymousClass1.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.keyToMetaRow.clear();
            List<MetaRow> rows = getRows();
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(rows, 10));
            for (MetaRow metaRow : rows) {
                arrayList2.add(new Pair(metaRowKey(metaRow), metaRow));
            }
            HashSet hashSet = new HashSet();
            ArrayList arrayList3 = new ArrayList();
            for (Object obj2 : arrayList2) {
                if (hashSet.add(((Pair) obj2).getFirst())) {
                    arrayList3.add(obj2);
                }
            }
            arrayList = arrayList3;
            it = arrayList.iterator();
        } else {
            if (i != 1 && i != 2 && i != 3) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            Object obj3 = anonymousClass1.L$3;
            Object obj4 = anonymousClass1.L$2;
            it = (Iterator) anonymousClass1.L$1;
            arrayList = (Iterable) anonymousClass1.L$0;
            ResultKt.throwOnFailure(obj);
        }
        while (it.hasNext()) {
            Object next = it.next();
            Pair pair = (Pair) next;
            Object objComponent1 = pair.component1();
            MetaRow metaRow2 = (MetaRow) pair.component2();
            this.keyToMetaRow.put(Boxing.boxLong(KeyUtilsKt.toLoungeModelKey(objComponent1)), metaRow2);
            int iSwitchDispatch = CatalogRowsFragment$$ExternalSyntheticTypeSwitchClass6.switchDispatch(metaRow2, 0);
            if (iSwitchDispatch == 0) {
                anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(arrayList);
                anonymousClass1.L$1 = it;
                anonymousClass1.L$2 = SpillingKt.nullOutSpilledVariable(next);
                anonymousClass1.L$3 = SpillingKt.nullOutSpilledVariable(objComponent1);
                anonymousClass1.L$4 = SpillingKt.nullOutSpilledVariable(metaRow2);
                anonymousClass1.label = 1;
                if (buildContinueWatchingRow(objComponent1, (MetaRow.ContinueWatchingRow) metaRow2, anonymousClass1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (iSwitchDispatch == 1) {
                anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(arrayList);
                anonymousClass1.L$1 = it;
                anonymousClass1.L$2 = SpillingKt.nullOutSpilledVariable(next);
                anonymousClass1.L$3 = SpillingKt.nullOutSpilledVariable(objComponent1);
                anonymousClass1.L$4 = SpillingKt.nullOutSpilledVariable(metaRow2);
                anonymousClass1.label = 2;
                if (buildLibraryRow(objComponent1, (MetaRow.LibraryRow) metaRow2, anonymousClass1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (iSwitchDispatch != 2) {
                    throw new NoWhenBranchMatchedException();
                }
                anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(arrayList);
                anonymousClass1.L$1 = it;
                anonymousClass1.L$2 = SpillingKt.nullOutSpilledVariable(next);
                anonymousClass1.L$3 = SpillingKt.nullOutSpilledVariable(objComponent1);
                anonymousClass1.L$4 = SpillingKt.nullOutSpilledVariable(metaRow2);
                anonymousClass1.label = 3;
                if (buildCatalogRow(objComponent1, (MetaRow.CatalogRow) metaRow2, anonymousClass1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        }
        return Unit.INSTANCE;
    }

    public final MetaRow metaRowFromKey(long key) {
        return this.keyToMetaRow.get(Long.valueOf(key));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object buildContinueWatchingRow(Object obj, MetaRow.ContinueWatchingRow continueWatchingRow, Continuation<? super Unit> continuation) {
        Object objListRowFor$default = ListRowModelKt.listRowFor$default(this, continueWatchingRow.getTitle(), continueWatchingRow.getItems(), obj, (ListRowPresenter) null, new Function1() { // from class: com.stremio.tv.views.metarow.CatalogRowsController$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                return CatalogRowsController.buildContinueWatchingRow$lambda$0(this.f$0, (LibraryItem) obj2);
            }
        }, continuation, 8, (Object) null);
        return objListRowFor$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objListRowFor$default : Unit.INSTANCE;
    }

    static final LoungeModel buildContinueWatchingRow$lambda$0(CatalogRowsController catalogRowsController, LibraryItem it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return new ContinueWatchingItemModel(it, catalogRowsController.getShowTitle());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object buildLibraryRow(Object obj, MetaRow.LibraryRow libraryRow, Continuation<? super Unit> continuation) {
        Object objListRowFor$default = ListRowModelKt.listRowFor$default(this, (String) null, libraryRow.getItems(), obj, (ListRowPresenter) null, new Function1() { // from class: com.stremio.tv.views.metarow.CatalogRowsController$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                return CatalogRowsController.buildLibraryRow$lambda$0(this.f$0, (LibraryItem) obj2);
            }
        }, continuation, 8, (Object) null);
        return objListRowFor$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objListRowFor$default : Unit.INSTANCE;
    }

    static final LoungeModel buildLibraryRow$lambda$0(CatalogRowsController catalogRowsController, LibraryItem it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return new LibraryItemModel(it, catalogRowsController.getShowTitle());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object buildCatalogRow(Object obj, MetaRow.CatalogRow catalogRow, Continuation<? super Unit> continuation) {
        ResourcePath path;
        Error error;
        String type = null;
        String strTitle = catalogRow.getShowTitle() ? CatalogExtKt.title(catalogRow.getCatalog(), StringsProvider.INSTANCE.getCurrent()) : null;
        if (LoadableExtKt.isReady(catalogRow.getCatalog())) {
            List<LoadablePage> pages = catalogRow.getCatalog().getPages();
            ArrayList arrayList = new ArrayList();
            Iterator<T> it = pages.iterator();
            while (it.hasNext()) {
                CollectionsKt.addAll(arrayList, LoadableExtKt.getAsReady((LoadablePage) it.next()));
            }
            HashSet hashSet = new HashSet();
            ArrayList arrayList2 = new ArrayList();
            for (Object obj2 : arrayList) {
                if (hashSet.add(((MetaItemPreview) obj2).getId())) {
                    arrayList2.add(obj2);
                }
            }
            Object objListRowFor$default = ListRowModelKt.listRowFor$default(this, strTitle, arrayList2, obj, (ListRowPresenter) null, new Function1() { // from class: com.stremio.tv.views.metarow.CatalogRowsController$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj3) {
                    return CatalogRowsController.buildCatalogRow$lambda$2(this.f$0, (MetaItemPreview) obj3);
                }
            }, continuation, 8, (Object) null);
            return objListRowFor$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objListRowFor$default : Unit.INSTANCE;
        }
        if (LoadableExtKt.isError(catalogRow.getCatalog())) {
            if (catalogRow.getShowOnError()) {
                LoadablePage loadablePage = (LoadablePage) CollectionsKt.firstOrNull((List) catalogRow.getCatalog().getPages());
                if (loadablePage != null && (error = loadablePage.getError()) != null) {
                    type = error.getMessage();
                }
                Object objListRowFor$default2 = ListRowModelKt.listRowFor$default(this, strTitle, CollectionsKt.listOf(type != null ? type : ""), obj, (ListRowPresenter) null, new Function1() { // from class: com.stremio.tv.views.metarow.CatalogRowsController$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj3) {
                        return CatalogRowsController.buildCatalogRow$lambda$3((String) obj3);
                    }
                }, continuation, 8, (Object) null);
                return objListRowFor$default2 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objListRowFor$default2 : Unit.INSTANCE;
            }
            return Unit.INSTANCE;
        }
        ArrayList arrayList3 = new ArrayList(10);
        for (int i = 0; i < 10; i++) {
            arrayList3.add(new Object());
        }
        ArrayList arrayList4 = arrayList3;
        ResourceRequest request = LoadableExtKt.getRequest(catalogRow.getCatalog());
        if (request != null && (path = request.getPath()) != null) {
            type = path.getType();
        }
        final String str = type != null ? type : "";
        Object objListRowForIndexed$default = ListRowModelKt.listRowForIndexed$default(this, strTitle, arrayList4, obj, (ListRowPresenter) null, new Function2() { // from class: com.stremio.tv.views.metarow.CatalogRowsController$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj3, Object obj4) {
                return CatalogRowsController.buildCatalogRow$lambda$5(str, ((Integer) obj3).intValue(), obj4);
            }
        }, continuation, 8, (Object) null);
        return objListRowForIndexed$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objListRowForIndexed$default : Unit.INSTANCE;
    }

    static final LoungeModel buildCatalogRow$lambda$2(CatalogRowsController catalogRowsController, MetaItemPreview it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return new MetaItemModel(it, catalogRowsController.getShowTitle());
    }

    static final LoungeModel buildCatalogRow$lambda$3(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return new CatalogErrorModel(it);
    }

    static final LoungeModel buildCatalogRow$lambda$5(String str, int i, Object obj) {
        Intrinsics.checkNotNullParameter(obj, "<unused var>");
        return new CatalogLoadingModel(i, str, PosterShape.POSTER.INSTANCE);
    }

    private final Object metaRowKey(MetaRow row) {
        int iSwitchDispatch = CatalogRowsFragment$$ExternalSyntheticTypeSwitchClass6.switchDispatch(row, 0);
        if (iSwitchDispatch == 0) {
            return ((MetaRow.ContinueWatchingRow) row).getTitle();
        }
        if (iSwitchDispatch == 1) {
            MetaRow.LibraryRow libraryRow = (MetaRow.LibraryRow) row;
            String title = libraryRow.getTitle();
            return title == null ? libraryRow.getItems() : title;
        }
        if (iSwitchDispatch != 2) {
            throw new NoWhenBranchMatchedException();
        }
        MetaRow.CatalogRow catalogRow = (MetaRow.CatalogRow) row;
        ResourceRequest request = LoadableExtKt.getRequest(catalogRow.getCatalog());
        return request != null ? request : catalogRow.getCatalog();
    }

    @Override // jp.co.cyberagent.lounge.LoungeController, java.lang.AutoCloseable
    public void close() {
        super.close();
    }
}