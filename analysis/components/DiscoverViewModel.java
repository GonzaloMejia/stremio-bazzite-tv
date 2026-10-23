package com.stremio.common.viewmodels;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.stremio.common.api.StremioApi;
import com.stremio.common.viewmodels.state.DiscoverState;
import com.stremio.core.Field;
import com.stremio.core.models.CatalogWithFilters;
import com.stremio.core.types.addon.ExtraValue;
import com.stremio.core.types.addon.ExtraValueType;
import com.stremio.core.types.addon.ResourcePath;
import com.stremio.core.types.addon.ResourceRequest;
import com.stremio.core.types.addon.ResourceType;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* JADX INFO: compiled from: DiscoverViewModel.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J.\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00102\b\u0010\u0012\u001a\u0004\u0018\u00010\u00102\b\u0010\u0013\u001a\u0004\u0018\u00010\u0010J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u0015J\u0010\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\b\u0010\u0019\u001a\u00020\u000eH\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fÊ\u0001\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\u0003\u0010\u0000¨\u0006\u001a"}, d2 = {"Lcom/stremio/common/viewmodels/DiscoverViewModel;", "Landroidx/lifecycle/ViewModel;", "stremioApi", "Lcom/stremio/common/api/StremioApi;", "<init>", "(Lcom/stremio/common/api/StremioApi;)V", "_state", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/stremio/common/viewmodels/state/DiscoverState;", "state", "Lkotlinx/coroutines/flow/StateFlow;", "getState", "()Lkotlinx/coroutines/flow/StateFlow;", "loadCatalog", "", "catalogAddonUrl", "", "type", "catalogId", "extra", "request", "Lcom/stremio/core/types/addon/ResourceRequest;", "toState", "catalogWithFilters", "Lcom/stremio/core/models/CatalogWithFilters;", "onCleared", "Stremio:common_release", "Landroidx/compose/runtime/internal/StabilityInferred;", "parameters"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class DiscoverViewModel extends ViewModel {
    public static final int $stable = 8;
    private final MutableStateFlow<DiscoverState> _state;
    private final StateFlow<DiscoverState> state;
    private final StremioApi stremioApi;

    public DiscoverViewModel(StremioApi stremioApi) {
        Intrinsics.checkNotNullParameter(stremioApi, "stremioApi");
        this.stremioApi = stremioApi;
        MutableStateFlow<DiscoverState> MutableStateFlow = StateFlowKt.MutableStateFlow(new DiscoverState(null, null, null, null, null, 31, null));
        this._state = MutableStateFlow;
        this.state = FlowKt.asStateFlow(MutableStateFlow);
        FlowKt.launchIn(FlowKt.onEach(FlowKt.onEach(new DiscoverViewModel$special$$inlined$map$1(stremioApi.discover(), this), new AnonymousClass2(null)), new AnonymousClass3(null)), ViewModelKt.getViewModelScope(this));
    }

    public final StateFlow<DiscoverState> getState() {
        return this.state;
    }

    /* JADX INFO: renamed from: com.stremio.common.viewmodels.DiscoverViewModel$2, reason: invalid class name */
    /* JADX INFO: compiled from: DiscoverViewModel.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "it", "Lcom/stremio/common/viewmodels/state/DiscoverState;"}, k = 3, mv = {2, 4, 0}, xi = 1328)
    @DebugMetadata(c = "com.stremio.common.viewmodels.DiscoverViewModel$2", f = "DiscoverViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass2 extends SuspendLambda implements Function2<DiscoverState, Continuation<? super Unit>, Object> {
        /* synthetic */ Object L$0;
        int label;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = DiscoverViewModel.this.new AnonymousClass2(continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(DiscoverState discoverState, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(discoverState, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            DiscoverState discoverState = (DiscoverState) this.L$0;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            DiscoverViewModel.this._state.setValue(discoverState);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.stremio.common.viewmodels.DiscoverViewModel$3, reason: invalid class name */
    /* JADX INFO: compiled from: DiscoverViewModel.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "it", "Lcom/stremio/common/viewmodels/state/DiscoverState;"}, k = 3, mv = {2, 4, 0}, xi = 1328)
    @DebugMetadata(c = "com.stremio.common.viewmodels.DiscoverViewModel$3", f = "DiscoverViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass3 extends SuspendLambda implements Function2<DiscoverState, Continuation<? super Unit>, Object> {
        /* synthetic */ Object L$0;
        int label;

        AnonymousClass3(Continuation<? super AnonymousClass3> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass3 anonymousClass3 = DiscoverViewModel.this.new AnonymousClass3(continuation);
            anonymousClass3.L$0 = obj;
            return anonymousClass3;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(DiscoverState discoverState, Continuation<? super Unit> continuation) {
            return ((AnonymousClass3) create(discoverState, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CatalogWithFilters.SelectableType selectableType;
            ResourceRequest request;
            DiscoverState discoverState = (DiscoverState) this.L$0;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            if (discoverState.getCurrentRequest() == null && (selectableType = (CatalogWithFilters.SelectableType) CollectionsKt.firstOrNull((List) discoverState.getTypes())) != null && (request = selectableType.getRequest()) != null) {
                DiscoverViewModel.this.loadCatalog(request);
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: Code duplicated, block: B:9:0x002a  */
    public final void loadCatalog(String catalogAddonUrl, String type, String catalogId, String extra) {
        List listEmptyList;
        if (catalogAddonUrl == null || type == null || catalogId == null) {
            return;
        }
        String name = ResourceType.CATALOG.INSTANCE.getName();
        Intrinsics.checkNotNull(name);
        if (extra != null) {
            String name2 = ExtraValueType.GENRE.INSTANCE.getName();
            Intrinsics.checkNotNull(name2);
            listEmptyList = CollectionsKt.listOf(new ExtraValue(name2, extra, null, 4, null));
            if (listEmptyList == null) {
                listEmptyList = CollectionsKt.emptyList();
            }
        } else {
            listEmptyList = CollectionsKt.emptyList();
        }
        loadCatalog(new ResourceRequest(catalogAddonUrl, new ResourcePath(name, type, catalogId, listEmptyList, null, 16, null), null, 4, null));
    }

    public final void loadCatalog(ResourceRequest request) {
        Intrinsics.checkNotNullParameter(request, "request");
        if (Intrinsics.areEqual(this.state.getValue().getCurrentRequest(), request)) {
            return;
        }
        this._state.setValue(DiscoverState.copy$default(this.state.getValue(), null, null, null, null, request, 15, null));
        this.stremioApi.loadDiscoverCatalog(request);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final DiscoverState toState(CatalogWithFilters catalogWithFilters) {
        List<CatalogWithFilters.SelectableType> types = catalogWithFilters.getSelectable().getTypes();
        List<CatalogWithFilters.SelectableCatalog> catalogs = catalogWithFilters.getSelectable().getCatalogs();
        for (Object obj : catalogWithFilters.getSelectable().getExtra()) {
            if (Intrinsics.areEqual(((CatalogWithFilters.SelectableExtra) obj).getName(), ExtraValueType.GENRE.INSTANCE.getName())) {
                return DiscoverState.copy$default(this.state.getValue(), types, catalogs, (CatalogWithFilters.SelectableExtra) obj, catalogWithFilters.getCatalog(), null, 16, null);
            }
        }
        obj = null;
        return DiscoverState.copy$default(this.state.getValue(), types, catalogs, (CatalogWithFilters.SelectableExtra) obj, catalogWithFilters.getCatalog(), null, 16, null);
    }

    @Override // androidx.lifecycle.ViewModel
    protected void onCleared() {
        super.onCleared();
        this.stremioApi.clearState(Field.DISCOVER.INSTANCE);
    }
}