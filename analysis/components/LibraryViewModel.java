package com.stremio.common.viewmodels;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.stremio.common.api.StremioApi;
import com.stremio.common.viewmodels.state.LibraryState;
import com.stremio.core.models.LibraryWithFilters;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
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

/* JADX INFO: compiled from: LibraryViewModel.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\r\u001a\u00020\u000eJ\u0010\u0010\u000f\u001a\u00020\u000e2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011J\u000e\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u0014J\u0010\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\u0017H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fÊ\u0001\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\u0003\u0010\u0000¨\u0006\u0018"}, d2 = {"Lcom/stremio/common/viewmodels/LibraryViewModel;", "Landroidx/lifecycle/ViewModel;", "stremioApi", "Lcom/stremio/common/api/StremioApi;", "<init>", "(Lcom/stremio/common/api/StremioApi;)V", "_state", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/stremio/common/viewmodels/state/LibraryState;", "state", "Lkotlinx/coroutines/flow/StateFlow;", "getState", "()Lkotlinx/coroutines/flow/StateFlow;", "load", "", "loadType", "type", "", "loadSort", "sort", "Lcom/stremio/core/models/LibraryWithFilters$Sort;", "toState", "libraryWithFilters", "Lcom/stremio/core/models/LibraryWithFilters;", "Stremio:common_release", "Landroidx/compose/runtime/internal/StabilityInferred;", "parameters"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class LibraryViewModel extends ViewModel {
    public static final int $stable = 8;
    private final MutableStateFlow<LibraryState> _state;
    private final StateFlow<LibraryState> state;
    private final StremioApi stremioApi;

    public LibraryViewModel(StremioApi stremioApi) {
        Intrinsics.checkNotNullParameter(stremioApi, "stremioApi");
        this.stremioApi = stremioApi;
        MutableStateFlow<LibraryState> MutableStateFlow = StateFlowKt.MutableStateFlow(new LibraryState(null, null, null, null, null, 31, null));
        this._state = MutableStateFlow;
        this.state = FlowKt.asStateFlow(MutableStateFlow);
        FlowKt.launchIn(FlowKt.onEach(new LibraryViewModel$special$$inlined$map$1(stremioApi.libraryWithFilters(), this), new AnonymousClass2(null)), ViewModelKt.getViewModelScope(this));
    }

    public final StateFlow<LibraryState> getState() {
        return this.state;
    }

    /* JADX INFO: renamed from: com.stremio.common.viewmodels.LibraryViewModel$2, reason: invalid class name */
    /* JADX INFO: compiled from: LibraryViewModel.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "it", "Lcom/stremio/common/viewmodels/state/LibraryState;"}, k = 3, mv = {2, 4, 0}, xi = 1328)
    @DebugMetadata(c = "com.stremio.common.viewmodels.LibraryViewModel$2", f = "LibraryViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass2 extends SuspendLambda implements Function2<LibraryState, Continuation<? super Unit>, Object> {
        /* synthetic */ Object L$0;
        int label;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = LibraryViewModel.this.new AnonymousClass2(continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(LibraryState libraryState, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(libraryState, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            LibraryState libraryState = (LibraryState) this.L$0;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            LibraryViewModel.this._state.setValue(libraryState);
            return Unit.INSTANCE;
        }
    }

    public final void load() {
        StremioApi stremioApi = this.stremioApi;
        String type = this.state.getValue().getType();
        LibraryWithFilters.Sort.LAST_WATCHED sort = this.state.getValue().getSort();
        if (sort == null) {
            sort = LibraryWithFilters.Sort.LAST_WATCHED.INSTANCE;
        }
        stremioApi.loadLibraryWithFilters(new LibraryWithFilters.LibraryRequest(type, sort, 1L, null, 8, null));
    }

    public final void loadType(String type) {
        StremioApi stremioApi = this.stremioApi;
        LibraryWithFilters.Sort.LAST_WATCHED sort = this.state.getValue().getSort();
        if (sort == null) {
            sort = LibraryWithFilters.Sort.LAST_WATCHED.INSTANCE;
        }
        stremioApi.loadLibraryWithFilters(new LibraryWithFilters.LibraryRequest(type, sort, 1L, null, 8, null));
    }

    public final void loadSort(LibraryWithFilters.Sort sort) {
        Intrinsics.checkNotNullParameter(sort, "sort");
        this.stremioApi.loadLibraryWithFilters(new LibraryWithFilters.LibraryRequest(this.state.getValue().getType(), sort, 1L, null, 8, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final LibraryState toState(LibraryWithFilters libraryWithFilters) {
        LibraryWithFilters.LibraryRequest request;
        LibraryWithFilters.LibraryRequest request2;
        LibraryState value = this.state.getValue();
        List<LibraryWithFilters.SelectableType> types = libraryWithFilters.getSelectable().getTypes();
        List<LibraryWithFilters.SelectableSort> sorts = libraryWithFilters.getSelectable().getSorts();
        LibraryWithFilters.Selected selected = libraryWithFilters.getSelected();
        LibraryWithFilters.Sort sort = null;
        String type = (selected == null || (request2 = selected.getRequest()) == null) ? null : request2.getType();
        LibraryWithFilters.Selected selected2 = libraryWithFilters.getSelected();
        if (selected2 != null && (request = selected2.getRequest()) != null) {
            sort = request.getSort();
        }
        return value.copy(types, sorts, type, sort, libraryWithFilters.getCatalog());
    }
}