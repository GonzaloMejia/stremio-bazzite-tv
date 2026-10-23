package com.stremio.common.viewmodels;

import androidx.compose.foundation.style.StylePropertiesKt;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.stremio.common.api.StremioApi;
import com.stremio.core.models.Ctx;
import com.stremio.core.models.UserProfile;
import com.stremio.core.runtime.msg.Event;
import java.util.Iterator;
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
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableSharedFlow;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.SharedFlow;
import kotlinx.coroutines.flow.SharedFlowKt;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* JADX INFO: compiled from: UserProfilesViewModel.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001c\u0010\u0018\u001a\u00020\u00132\b\u0010\u0019\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u000eJ\u0006\u0010\u001b\u001a\u00020\u0013J\u0010\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u001d\u001a\u00020\u001eH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0016\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\u000f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\n¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00130\u0015¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017Ê\u0001\f\b \u0012\b\b!\u0012\u0004\b\u0003\u0010\u0000¨\u0006\u001f"}, d2 = {"Lcom/stremio/common/viewmodels/UserProfilesViewModel;", "Landroidx/lifecycle/ViewModel;", "stremioApi", "Lcom/stremio/common/api/StremioApi;", "<init>", "(Lcom/stremio/common/api/StremioApi;)V", "_state", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/stremio/common/viewmodels/UserProfilesState;", "state", "Lkotlinx/coroutines/flow/StateFlow;", "getState", "()Lkotlinx/coroutines/flow/StateFlow;", "_profileSwitchError", "", "profileSwitchError", "getProfileSwitchError", "_profileSwitched", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "", "profileSwitched", "Lkotlinx/coroutines/flow/SharedFlow;", "getProfileSwitched", "()Lkotlinx/coroutines/flow/SharedFlow;", "switchProfile", "profileId", "pin", "clearProfileSwitchError", "updateState", "value", "Lcom/stremio/core/models/Ctx;", "Stremio:common_release", "Landroidx/compose/runtime/internal/StabilityInferred;", "parameters"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class UserProfilesViewModel extends ViewModel {
    public static final int $stable = 8;
    private final MutableStateFlow<String> _profileSwitchError;
    private final MutableSharedFlow<Unit> _profileSwitched;
    private final MutableStateFlow<UserProfilesState> _state;
    private final StateFlow<String> profileSwitchError;
    private final SharedFlow<Unit> profileSwitched;
    private final StateFlow<UserProfilesState> state;
    private final StremioApi stremioApi;

    public UserProfilesViewModel(StremioApi stremioApi) {
        Intrinsics.checkNotNullParameter(stremioApi, "stremioApi");
        this.stremioApi = stremioApi;
        MutableStateFlow<UserProfilesState> MutableStateFlow = StateFlowKt.MutableStateFlow(new UserProfilesState(null, null, false, 7, null));
        this._state = MutableStateFlow;
        this.state = FlowKt.asStateFlow(MutableStateFlow);
        MutableStateFlow<String> MutableStateFlow2 = StateFlowKt.MutableStateFlow(null);
        this._profileSwitchError = MutableStateFlow2;
        this.profileSwitchError = FlowKt.asStateFlow(MutableStateFlow2);
        MutableSharedFlow<Unit> mutableSharedFlowMutableSharedFlow$default = SharedFlowKt.MutableSharedFlow$default(0, 0, null, 7, null);
        this._profileSwitched = mutableSharedFlowMutableSharedFlow$default;
        this.profileSwitched = FlowKt.asSharedFlow(mutableSharedFlowMutableSharedFlow$default);
        UserProfilesViewModel userProfilesViewModel = this;
        FlowKt.launchIn(FlowKt.onEach(FlowKt.distinctUntilChanged(stremioApi.ctx()), new AnonymousClass1(null)), ViewModelKt.getViewModelScope(userProfilesViewModel));
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(userProfilesViewModel), null, null, new AnonymousClass2(null), 3, null);
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(userProfilesViewModel), null, null, new AnonymousClass3(null), 3, null);
    }

    public final StateFlow<UserProfilesState> getState() {
        return this.state;
    }

    public final StateFlow<String> getProfileSwitchError() {
        return this.profileSwitchError;
    }

    public final SharedFlow<Unit> getProfileSwitched() {
        return this.profileSwitched;
    }

    /* JADX INFO: renamed from: com.stremio.common.viewmodels.UserProfilesViewModel$1, reason: invalid class name */
    /* JADX INFO: compiled from: UserProfilesViewModel.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "it", "Lcom/stremio/core/models/Ctx;"}, k = 3, mv = {2, 4, 0}, xi = 1328)
    @DebugMetadata(c = "com.stremio.common.viewmodels.UserProfilesViewModel$1", f = "UserProfilesViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass1 extends SuspendLambda implements Function2<Ctx, Continuation<? super Unit>, Object> {
        /* synthetic */ Object L$0;
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = UserProfilesViewModel.this.new AnonymousClass1(continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Ctx ctx, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(ctx, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Ctx ctx = (Ctx) this.L$0;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            UserProfilesViewModel.this.updateState(ctx);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.stremio.common.viewmodels.UserProfilesViewModel$2, reason: invalid class name */
    /* JADX INFO: compiled from: UserProfilesViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 4, 0}, xi = 1328)
    @DebugMetadata(c = "com.stremio.common.viewmodels.UserProfilesViewModel$2", f = "UserProfilesViewModel.kt", i = {}, l = {43}, m = "invokeSuspend", n = {}, nl = {ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX}, s = {}, v = 2)
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return UserProfilesViewModel.this.new AnonymousClass2(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Flow<Event.Type<?>> flowCoreEvents = UserProfilesViewModel.this.stremioApi.coreEvents();
                final UserProfilesViewModel userProfilesViewModel = UserProfilesViewModel.this;
                this.label = 1;
                if (flowCoreEvents.collect(new FlowCollector() { // from class: com.stremio.common.viewmodels.UserProfilesViewModel.2.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                        return emit((Event.Type<?>) obj2, (Continuation<? super Unit>) continuation);
                    }

                    public final Object emit(Event.Type<?> type, Continuation<? super Unit> continuation) {
                        if (type instanceof Event.Type.ProfileSwitched) {
                            Object objEmit = userProfilesViewModel._profileSwitched.emit(Unit.INSTANCE, continuation);
                            return objEmit == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objEmit : Unit.INSTANCE;
                        }
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

    /* JADX INFO: renamed from: com.stremio.common.viewmodels.UserProfilesViewModel$3, reason: invalid class name */
    /* JADX INFO: compiled from: UserProfilesViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 4, 0}, xi = 1328)
    @DebugMetadata(c = "com.stremio.common.viewmodels.UserProfilesViewModel$3", f = "UserProfilesViewModel.kt", i = {}, l = {52}, m = "invokeSuspend", n = {}, nl = {StylePropertiesKt.ContentBrushId}, s = {}, v = 2)
    static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass3(Continuation<? super AnonymousClass3> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return UserProfilesViewModel.this.new AnonymousClass3(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Flow<Event.Error> flowErrors = UserProfilesViewModel.this.stremioApi.errors();
                final UserProfilesViewModel userProfilesViewModel = UserProfilesViewModel.this;
                this.label = 1;
                if (flowErrors.collect(new FlowCollector() { // from class: com.stremio.common.viewmodels.UserProfilesViewModel.3.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                        return emit((Event.Error) obj2, (Continuation<? super Unit>) continuation);
                    }

                    public final Object emit(Event.Error error, Continuation<? super Unit> continuation) {
                        if (error.getSource().getType() instanceof Event.Type.ProfileSwitched) {
                            userProfilesViewModel._profileSwitchError.setValue(error.getError());
                        }
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

    public static /* synthetic */ void switchProfile$default(UserProfilesViewModel userProfilesViewModel, String str, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = null;
        }
        userProfilesViewModel.switchProfile(str, str2);
    }

    public final void switchProfile(String profileId, String pin) {
        this._profileSwitchError.setValue(null);
        this.stremioApi.switchProfile(profileId, pin);
    }

    public final void clearProfileSwitchError() {
        this._profileSwitchError.setValue(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateState(Ctx value) {
        Object next;
        MutableStateFlow<UserProfilesState> mutableStateFlow = this._state;
        UserProfilesState value2 = this.state.getValue();
        List<UserProfile> userProfiles = value.getUserProfiles();
        Iterator<T> it = value.getUserProfiles().iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!Intrinsics.areEqual((Object) ((UserProfile) next).getSelected(), (Object) true));
        mutableStateFlow.setValue(value2.copy(userProfiles, (UserProfile) next, value.getUserProfiles().size() >= 2));
    }
}