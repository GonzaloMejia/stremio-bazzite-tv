package com.stremio.common.viewmodels;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.stremio.common.api.StremioApi;
import com.stremio.common.viewmodels.state.BoardState;
import com.stremio.core.Field;
import com.stremio.core.models.Catalog;
import com.stremio.core.types.library.LibraryItem;
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

/* JADX INFO: compiled from: BoardViewModel.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\r\u001a\u00020\u000eH\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fÊ\u0001\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\u0003\u0010\u0000¨\u0006\u000f"}, d2 = {"Lcom/stremio/common/viewmodels/BoardViewModel;", "Landroidx/lifecycle/ViewModel;", "stremioApi", "Lcom/stremio/common/api/StremioApi;", "<init>", "(Lcom/stremio/common/api/StremioApi;)V", "_state", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/stremio/common/viewmodels/state/BoardState;", "state", "Lkotlinx/coroutines/flow/StateFlow;", "getState", "()Lkotlinx/coroutines/flow/StateFlow;", "onCleared", "", "Stremio:common_release", "Landroidx/compose/runtime/internal/StabilityInferred;", "parameters"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class BoardViewModel extends ViewModel {
    public static final int $stable = 8;
    private final MutableStateFlow<BoardState> _state;
    private final StateFlow<BoardState> state;
    private final StremioApi stremioApi;

    /* JADX WARN: Multi-variable type inference failed */
    public BoardViewModel(StremioApi stremioApi) {
        Intrinsics.checkNotNullParameter(stremioApi, "stremioApi");
        this.stremioApi = stremioApi;
        MutableStateFlow<BoardState> MutableStateFlow = StateFlowKt.MutableStateFlow(new BoardState(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0));
        this._state = MutableStateFlow;
        this.state = FlowKt.asStateFlow(MutableStateFlow);
        BoardViewModel boardViewModel = this;
        FlowKt.launchIn(FlowKt.onEach(new BoardViewModel$special$$inlined$map$1(stremioApi.continueWatchingPreview()), new AnonymousClass2(null)), ViewModelKt.getViewModelScope(boardViewModel));
        FlowKt.launchIn(FlowKt.onEach(new BoardViewModel$special$$inlined$map$2(stremioApi.board()), new AnonymousClass4(null)), ViewModelKt.getViewModelScope(boardViewModel));
    }

    public final StateFlow<BoardState> getState() {
        return this.state;
    }

    /* JADX INFO: renamed from: com.stremio.common.viewmodels.BoardViewModel$2, reason: invalid class name */
    /* JADX INFO: compiled from: BoardViewModel.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\n"}, d2 = {"<anonymous>", "", "it", "", "Lcom/stremio/core/types/library/LibraryItem;"}, k = 3, mv = {2, 4, 0}, xi = 1328)
    @DebugMetadata(c = "com.stremio.common.viewmodels.BoardViewModel$2", f = "BoardViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass2 extends SuspendLambda implements Function2<List<? extends LibraryItem>, Continuation<? super Unit>, Object> {
        /* synthetic */ Object L$0;
        int label;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = BoardViewModel.this.new AnonymousClass2(continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(List<? extends LibraryItem> list, Continuation<? super Unit> continuation) {
            return invoke2((List<LibraryItem>) list, continuation);
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(List<LibraryItem> list, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(list, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            List list = (List) this.L$0;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            BoardViewModel.this._state.setValue(BoardState.copy$default(BoardViewModel.this.getState().getValue(), list, null, 2, null));
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.stremio.common.viewmodels.BoardViewModel$4, reason: invalid class name */
    /* JADX INFO: compiled from: BoardViewModel.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\n"}, d2 = {"<anonymous>", "", "it", "", "Lcom/stremio/core/models/Catalog;"}, k = 3, mv = {2, 4, 0}, xi = 1328)
    @DebugMetadata(c = "com.stremio.common.viewmodels.BoardViewModel$4", f = "BoardViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass4 extends SuspendLambda implements Function2<List<? extends Catalog>, Continuation<? super Unit>, Object> {
        /* synthetic */ Object L$0;
        int label;

        AnonymousClass4(Continuation<? super AnonymousClass4> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass4 anonymousClass4 = BoardViewModel.this.new AnonymousClass4(continuation);
            anonymousClass4.L$0 = obj;
            return anonymousClass4;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(List<? extends Catalog> list, Continuation<? super Unit> continuation) {
            return invoke2((List<Catalog>) list, continuation);
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(List<Catalog> list, Continuation<? super Unit> continuation) {
            return ((AnonymousClass4) create(list, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            List list = (List) this.L$0;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            BoardViewModel.this._state.setValue(BoardState.copy$default(BoardViewModel.this.getState().getValue(), null, list, 1, null));
            return Unit.INSTANCE;
        }
    }

    @Override // androidx.lifecycle.ViewModel
    protected void onCleared() {
        super.onCleared();
        this.stremioApi.clearState(Field.BOARD.INSTANCE);
        this.stremioApi.clearState(Field.CONTINUE_WATCHING_PREVIEW.INSTANCE);
    }
}