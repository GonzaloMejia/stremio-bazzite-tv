// [jadx java-decompile failed → smali]
// target: com.stremio.common.viewmodels.SearchViewModel
// markers: Code decompiled incorrectly, please refer to instructions dump
// (Java body was an anti-decompile stub; showing smali. Pass smali_fallback=false for the partial Java.)

###### Class com.stremio.common.viewmodels.SearchViewModel (com.stremio.common.viewmodels.SearchViewModel)
.class public final Lcom/stremio/common/viewmodels/SearchViewModel;
.super Landroidx/lifecycle/ViewModel;
.source "SearchViewModel.kt"


# annotations
.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0010\u000e\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0010\u0002\n\u0002\u0008\u0005\n\u0002\u0018\u0002\n\u0000\u0008\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\u0008\u0004\u0010\u0005J\u000e\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u000fJ\u000e\u0010\u001e\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u000fJ\u0006\u0010\u001f\u001a\u00020\u001cJ\u0008\u0010 \u001a\u00020\u001cH\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u000e\u0012\n\u0012\u0008\u0012\u0004\u0012\u00020\t0\u00080\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\n\u001a\u000e\u0012\n\u0012\u0008\u0012\u0004\u0012\u00020\t0\u00080\u000b\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008\u000c\u0010\rR\u001a\u0010\u000e\u001a\u000e\u0012\n\u0012\u0008\u0012\u0004\u0012\u00020\u000f0\u00080\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0010\u001a\u000e\u0012\n\u0012\u0008\u0012\u0004\u0012\u00020\u000f0\u00080\u000b\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008\u0011\u0010\rR\u001a\u0010\u0012\u001a\u000e\u0012\n\u0012\u0008\u0012\u0004\u0012\u00020\u000f0\u00080\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0014\u001a\u000e\u0012\n\u0012\u0008\u0012\u0004\u0012\u00020\u000f0\u00080\u0015\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008\u0016\u0010\u0017R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u0019X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00ca\u0001\u000c\u0008\"\u0012\u0008\u0008#\u0012\u0004\u0008\u0003\u0010\u0000\u00a8\u0006!"
    }
    d2 = {
        "Lcom/stremio/common/viewmodels/SearchViewModel;",
        "Landroidx/lifecycle/ViewModel;",
        "stremioApi",
        "Lcom/stremio/common/api/StremioApi;",
        "<init>",
        "(Lcom/stremio/common/api/StremioApi;)V",
        "_state",
        "Lkotlinx/coroutines/flow/MutableStateFlow;",
        "",
        "Lcom/stremio/core/models/Catalog;",
        "state",
        "Lkotlinx/coroutines/flow/StateFlow;",
        "getState",
        "()Lkotlinx/coroutines/flow/StateFlow;",
        "_history",
        "",
        "history",
        "getHistory",
        "_suggestions",
        "Lkotlinx/coroutines/flow/MutableSharedFlow;",
        "suggestions",
        "Lkotlinx/coroutines/flow/SharedFlow;",
        "getSuggestions",
        "()Lkotlinx/coroutines/flow/SharedFlow;",
        "searchJob",
        "Lkotlinx/coroutines/Job;",
        "searchSuggestionsJob",
        "search",
        "",
        "query",
        "searchSuggestions",
        "clearHistory",
        "onCleared",
        "Stremio:common_release",
        "Landroidx/compose/runtime/internal/StabilityInferred;",
        "parameters"
    }
    k = 0x1
    mv = {
        0x2,
        0x4,
        0x0
    }
    xi = 0x30
.end annotation


# static fields
.field public static final $stable:I = 0x8


# instance fields
.field private final _history:Lkotlinx/coroutines/flow/MutableStateFlow;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lkotlinx/coroutines/flow/MutableStateFlow<",
            "Ljava/util/List<",
            "Ljava/lang/String;",
            ">;>;"
        }
    .end annotation
.end field

.field private final _state:Lkotlinx/coroutines/flow/MutableStateFlow;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lkotlinx/coroutines/flow/MutableStateFlow<",
            "Ljava/util/List<",
            "Lcom/stremio/core/models/Catalog;",
            ">;>;"
        }
    .end annotation
.end field

.field private final _suggestions:Lkotlinx/coroutines/flow/MutableSharedFlow;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lkotlinx/coroutines/flow/MutableSharedFlow<",
            "Ljava/util/List<",
            "Ljava/lang/String;",
            ">;>;"
        }
    .end annotation
.end field

.field private final history:Lkotlinx/coroutines/flow/StateFlow;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lkotlinx/coroutines/flow/StateFlow<",
            "Ljava/util/List<",
            "Ljava/lang/String;",
            ">;>;"
        }
    .end annotation
.end field

.field private searchJob:Lkotlinx/coroutines/Job;

.field private searchSuggestionsJob:Lkotlinx/coroutines/Job;

.field private final state:Lkotlinx/coroutines/flow/StateFlow;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lkotlinx/coroutines/flow/StateFlow<",
            "Ljava/util/List<",
            "Lcom/stremio/core/models/Catalog;",
            ">;>;"
        }
    .end annotation
.end field

.field private final stremioApi:Lcom/stremio/common/api/StremioApi;

.field private final suggestions:Lkotlinx/coroutines/flow/SharedFlow;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lkotlinx/coroutines/flow/SharedFlow<",
            "Ljava/util/List<",
            "Ljava/lang/String;",
            ">;>;"
        }
    .end annotation
.end field


# direct methods
.method static constructor <clinit>()V
    .registers 0

    return-void
.end method

.method public constructor <init>(Lcom/stremio/common/api/StremioApi;)V
    .registers 5

    const-string v0, "stremioApi"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 19
    invoke-direct {p0}, Landroidx/lifecycle/ViewModel;-><init>()V

    iput-object p1, p0, Lcom/stremio/common/viewmodels/SearchViewModel;->stremioApi:Lcom/stremio/common/api/StremioApi;

    .line 20
    invoke-static {}, Lkotlin/collections/CollectionsKt;->emptyList()Ljava/util/List;

    move-result-object v0

    invoke-static {v0}, Lkotlinx/coroutines/flow/StateFlowKt;->MutableStateFlow(Ljava/lang/Object;)Lkotlinx/coroutines/flow/MutableStateFlow;

    move-result-object v0

    iput-object v0, p0, Lcom/stremio/common/viewmodels/SearchViewModel;->_state:Lkotlinx/coroutines/flow/MutableStateFlow;

    .line 21
    invoke-static {v0}, Lkotlinx/coroutines/flow/FlowKt;->asStateFlow(Lkotlinx/coroutines/flow/MutableStateFlow;)Lkotlinx/coroutines/flow/StateFlow;

    move-result-object v0

    iput-object v0, p0, Lcom/stremio/common/viewmodels/SearchViewModel;->state:Lkotlinx/coroutines/flow/StateFlow;

    .line 23
    invoke-static {}, Lkotlin/collections/CollectionsKt;->emptyList()Ljava/util/List;

    move-result-object v0

    invoke-static {v0}, Lkotlinx/coroutines/flow/StateFlowKt;->MutableStateFlow(Ljava/lang/Object;)Lkotlinx/coroutines/flow/MutableStateFlow;

    move-result-object v0

    iput-object v0, p0, Lcom/stremio/common/viewmodels/SearchViewModel;->_history:Lkotlinx/coroutines/flow/MutableStateFlow;

    .line 24
    invoke-static {v0}, Lkotlinx/coroutines/flow/FlowKt;->asStateFlow(Lkotlinx/coroutines/flow/MutableStateFlow;)Lkotlinx/coroutines/flow/StateFlow;

    move-result-object v0

    iput-object v0, p0, Lcom/stremio/common/viewmodels/SearchViewModel;->history:Lkotlinx/coroutines/flow/StateFlow;

    const/4 v0, 0x7

    const/4 v1, 0x0

    const/4 v2, 0x0

    .line 26
    invoke-static {v1, v1, v2, v0, v2}, Lkotlinx/coroutines/flow/SharedFlowKt;->MutableSharedFlow$default(IILkotlinx/coroutines/channels/BufferOverflow;ILjava/lang/Object;)Lkotlinx/coroutines/flow/MutableSharedFlow;

    move-result-object v0

    iput-object v0, p0, Lcom/stremio/common/viewmodels/SearchViewModel;->_suggestions:Lkotlinx/coroutines/flow/MutableSharedFlow;

    .line 27
    invoke-static {v0}, Lkotlinx/coroutines/flow/FlowKt;->asSharedFlow(Lkotlinx/coroutines/flow/MutableSharedFlow;)Lkotlinx/coroutines/flow/SharedFlow;

    move-result-object v0

    iput-object v0, p0, Lcom/stremio/common/viewmodels/SearchViewModel;->suggestions:Lkotlinx/coroutines/flow/SharedFlow;

    .line 33
    invoke-virtual {p1}, Lcom/stremio/common/api/StremioApi;->ctx()Lkotlinx/coroutines/flow/Flow;

    move-result-object p1

    .line 34
    invoke-static {p1}, Lkotlinx/coroutines/flow/FlowKt;->distinctUntilChanged(Lkotlinx/coroutines/flow/Flow;)Lkotlinx/coroutines/flow/Flow;

    move-result-object p1

    .line 35
    new-instance v0, Lcom/stremio/common/viewmodels/SearchViewModel$1;

    invoke-direct {v0, p0, v2}, Lcom/stremio/common/viewmodels/SearchViewModel$1;-><init>(Lcom/stremio/common/viewmodels/SearchViewModel;Lkotlin/coroutines/Continuation;)V

    check-cast v0, Lkotlin/jvm/functions/Function2;

    invoke-static {p1, v0}, Lkotlinx/coroutines/flow/FlowKt;->onEach(Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/flow/Flow;

    move-result-object p1

    .line 39
    check-cast p0, Landroidx/lifecycle/ViewModel;

    invoke-static {p0}, Landroidx/lifecycle/ViewModelKt;->getViewModelScope(Landroidx/lifecycle/ViewModel;)Lkotlinx/coroutines/CoroutineScope;

    move-result-object p0

    invoke-static {p1, p0}, Lkotlinx/coroutines/flow/FlowKt;->launchIn(Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/CoroutineScope;)Lkotlinx/coroutines/Job;

    return-void
.end method

.method public static final synthetic access$getStremioApi$p(Lcom/stremio/common/viewmodels/SearchViewModel;)Lcom/stremio/common/api/StremioApi;
    .registers 1

    .line 19
    iget-object p0, p0, Lcom/stremio/common/viewmodels/SearchViewModel;->stremioApi:Lcom/stremio/common/api/StremioApi;

    return-object p0
.end method

.method public static final synthetic access$get_history$p(Lcom/stremio/common/viewmodels/SearchViewModel;)Lkotlinx/coroutines/flow/MutableStateFlow;
    .registers 1

    .line 19
    iget-object p0, p0, Lcom/stremio/common/viewmodels/SearchViewModel;->_history:Lkotlinx/coroutines/flow/MutableStateFlow;

    return-object p0
.end method

.method public static final synthetic access$get_state$p(Lcom/stremio/common/viewmodels/SearchViewModel;)Lkotlinx/coroutines/flow/MutableStateFlow;
    .registers 1

    .line 19
    iget-object p0, p0, Lcom/stremio/common/viewmodels/SearchViewModel;->_state:Lkotlinx/coroutines/flow/MutableStateFlow;

    return-object p0
.end method

.method public static final synthetic access$get_suggestions$p(Lcom/stremio/common/viewmodels/SearchViewModel;)Lkotlinx/coroutines/flow/MutableSharedFlow;
    .registers 1

    .line 19
    iget-object p0, p0, Lcom/stremio/common/viewmodels/SearchViewModel;->_suggestions:Lkotlinx/coroutines/flow/MutableSharedFlow;

    return-object p0
.end method


# virtual methods
.method public final clearHistory()V
    .registers 1

    .line 60
    iget-object p0, p0, Lcom/stremio/common/viewmodels/SearchViewModel;->stremioApi:Lcom/stremio/common/api/StremioApi;

    invoke-virtual {p0}, Lcom/stremio/common/api/StremioApi;->clearSearchHistory()V

    return-void
.end method

.method public final getHistory()Lkotlinx/coroutines/flow/StateFlow;
    .registers 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Lkotlinx/coroutines/flow/StateFlow<",
            "Ljava/util/List<",
            "Ljava/lang/String;",
            ">;>;"
        }
    .end annotation

    .line 24
    iget-object p0, p0, Lcom/stremio/common/viewmodels/SearchViewModel;->history:Lkotlinx/coroutines/flow/StateFlow;

    return-object p0
.end method

.method public final getState()Lkotlinx/coroutines/flow/StateFlow;
    .registers 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Lkotlinx/coroutines/flow/StateFlow<",
            "Ljava/util/List<",
            "Lcom/stremio/core/models/Catalog;",
            ">;>;"
        }
    .end annotation

    .line 21
    iget-object p0, p0, Lcom/stremio/common/viewmodels/SearchViewModel;->state:Lkotlinx/coroutines/flow/StateFlow;

    return-object p0
.end method

.method public final getSuggestions()Lkotlinx/coroutines/flow/SharedFlow;
    .registers 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Lkotlinx/coroutines/flow/SharedFlow<",
            "Ljava/util/List<",
            "Ljava/lang/String;",
            ">;>;"
        }
    .end annotation

    .line 27
    iget-object p0, p0, Lcom/stremio/common/viewmodels/SearchViewModel;->suggestions:Lkotlinx/coroutines/flow/SharedFlow;

    return-object p0
.end method

.method protected onCleared()V
    .registers 4

    .line 64
    invoke-super {p0}, Landroidx/lifecycle/ViewModel;->onCleared()V

    .line 65
    iget-object v0, p0, Lcom/stremio/common/viewmodels/SearchViewModel;->searchJob:Lkotlinx/coroutines/Job;

    const/4 v1, 0x1

    const/4 v2, 0x0

    if-eqz v0, :cond_c

    invoke-static {v0, v2, v1, v2}, Lkotlinx/coroutines/Job;->cancel$default(Lkotlinx/coroutines/Job;Ljava/util/concurrent/CancellationException;ILjava/lang/Object;)V

    .line 66
    :cond_c
    iget-object v0, p0, Lcom/stremio/common/viewmodels/SearchViewModel;->searchSuggestionsJob:Lkotlinx/coroutines/Job;

    if-eqz v0, :cond_13

    invoke-static {v0, v2, v1, v2}, Lkotlinx/coroutines/Job;->cancel$default(Lkotlinx/coroutines/Job;Ljava/util/concurrent/CancellationException;ILjava/lang/Object;)V

    .line 67
    :cond_13
    iget-object p0, p0, Lcom/stremio/common/viewmodels/SearchViewModel;->stremioApi:Lcom/stremio/common/api/StremioApi;

    sget-object v0, Lcom/stremio/core/Field$SEARCH;->INSTANCE:Lcom/stremio/core/Field$SEARCH;

    check-cast v0, Lcom/stremio/core/Field;

    invoke-virtual {p0, v0}, Lcom/stremio/common/api/StremioApi;->clearState(Lcom/stremio/core/Field;)V

    return-void
.end method

.method public final search(Ljava/lang/String;)V
    .registers 5

    const-string v0, "query"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 43
    iget-object v0, p0, Lcom/stremio/common/viewmodels/SearchViewModel;->_state:Lkotlinx/coroutines/flow/MutableStateFlow;

    invoke-static {}, Lkotlin/collections/CollectionsKt;->emptyList()Ljava/util/List;

    move-result-object v1

    invoke-interface {v0, v1}, Lkotlinx/coroutines/flow/MutableStateFlow;->setValue(Ljava/lang/Object;)V

    .line 44
    iget-object v0, p0, Lcom/stremio/common/viewmodels/SearchViewModel;->searchJob:Lkotlinx/coroutines/Job;

    const/4 v1, 0x0

    if-eqz v0, :cond_17

    const/4 v2, 0x1

    invoke-static {v0, v1, v2, v1}, Lkotlinx/coroutines/Job;->cancel$default(Lkotlinx/coroutines/Job;Ljava/util/concurrent/CancellationException;ILjava/lang/Object;)V

    .line 45
    :cond_17
    iget-object v0, p0, Lcom/stremio/common/viewmodels/SearchViewModel;->stremioApi:Lcom/stremio/common/api/StremioApi;

    invoke-virtual {v0, p1}, Lcom/stremio/common/api/StremioApi;->search(Ljava/lang/String;)Lkotlinx/coroutines/flow/Flow;

    move-result-object p1

    .line 46
    new-instance v0, Lcom/stremio/common/viewmodels/SearchViewModel$search$1;

    invoke-direct {v0, p0, v1}, Lcom/stremio/common/viewmodels/SearchViewModel$search$1;-><init>(Lcom/stremio/common/viewmodels/SearchViewModel;Lkotlin/coroutines/Continuation;)V

    check-cast v0, Lkotlin/jvm/functions/Function2;

    invoke-static {p1, v0}, Lkotlinx/coroutines/flow/FlowKt;->onEach(Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/flow/Flow;

    move-result-object p1

    .line 47
    move-object v0, p0

    check-cast v0, Landroidx/lifecycle/ViewModel;

    invoke-static {v0}, Landroidx/lifecycle/ViewModelKt;->getViewModelScope(Landroidx/lifecycle/ViewModel;)Lkotlinx/coroutines/CoroutineScope;

    move-result-object v0

    invoke-static {p1, v0}, Lkotlinx/coroutines/flow/FlowKt;->launchIn(Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/CoroutineScope;)Lkotlinx/coroutines/Job;

    move-result-object p1

    .line 45
    iput-object p1, p0, Lcom/stremio/common/viewmodels/SearchViewModel;->searchJob:Lkotlinx/coroutines/Job;

    return-void
.end method

.method public final searchSuggestions(Ljava/lang/String;)V
    .registers 10

    const-string v0, "query"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 51
    iget-object v0, p0, Lcom/stremio/common/viewmodels/SearchViewModel;->searchSuggestionsJob:Lkotlinx/coroutines/Job;

    const/4 v1, 0x0

    if-eqz v0, :cond_e

    const/4 v2, 0x1

    invoke-static {v0, v1, v2, v1}, Lkotlinx/coroutines/Job;->cancel$default(Lkotlinx/coroutines/Job;Ljava/util/concurrent/CancellationException;ILjava/lang/Object;)V

    .line 52
    :cond_e
    move-object v0, p0

    check-cast v0, Landroidx/lifecycle/ViewModel;

    invoke-static {v0}, Landroidx/lifecycle/ViewModelKt;->getViewModelScope(Landroidx/lifecycle/ViewModel;)Lkotlinx/coroutines/CoroutineScope;

    move-result-object v2

    new-instance v0, Lcom/stremio/common/viewmodels/SearchViewModel$searchSuggestions$1;

    invoke-direct {v0, p0, p1, v1}, Lcom/stremio/common/viewmodels/SearchViewModel$searchSuggestions$1;-><init>(Lcom/stremio/common/viewmodels/SearchViewModel;Ljava/lang/String;Lkotlin/coroutines/Continuation;)V

    move-object v5, v0

    check-cast v5, Lkotlin/jvm/functions/Function2;

    const/4 v6, 0x3

    const/4 v7, 0x0

    const/4 v3, 0x0

    const/4 v4, 0x0

    invoke-static/range {v2 .. v7}, Lkotlinx/coroutines/BuildersKt;->launch$default(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/CoroutineStart;Lkotlin/jvm/functions/Function2;ILjava/lang/Object;)Lkotlinx/coroutines/Job;

    move-result-object p1

    iput-object p1, p0, Lcom/stremio/common/viewmodels/SearchViewModel;->searchSuggestionsJob:Lkotlinx/coroutines/Job;

    return-void
.end method

###### Class com.stremio.common.viewmodels.SearchViewModel.AnonymousClass1 (com.stremio.common.viewmodels.SearchViewModel$1)
.class final Lcom/stremio/common/viewmodels/SearchViewModel$1;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "SearchViewModel.kt"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/stremio/common/viewmodels/SearchViewModel;-><init>(Lcom/stremio/common/api/StremioApi;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x18
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lkotlin/coroutines/jvm/internal/SuspendLambda;",
        "Lkotlin/jvm/functions/Function2<",
        "Lcom/stremio/core/models/Ctx;",
        "Lkotlin/coroutines/Continuation<",
        "-",
        "Lkotlin/Unit;",
        ">;",
        "Ljava/lang/Object;",
        ">;"
    }
.end annotation

.annotation system Ldalvik/annotation/SourceDebugExtension;
    value = "SMAP\nSearchViewModel.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SearchViewModel.kt\ncom/stremio/common/viewmodels/SearchViewModel$1\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,70:1\n1745#2:71\n1820#2,3:72\n*S KotlinDebug\n*F\n+ 1 SearchViewModel.kt\ncom/stremio/common/viewmodels/SearchViewModel$1\n*L\n37#1:71\n37#1:72,3\n*E\n"
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000\u000c\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"
    }
    d2 = {
        "<anonymous>",
        "",
        "ctx",
        "Lcom/stremio/core/models/Ctx;"
    }
    k = 0x3
    mv = {
        0x2,
        0x4,
        0x0
    }
    xi = 0x530
.end annotation

.annotation runtime Lkotlin/coroutines/jvm/internal/DebugMetadata;
    c = "com.stremio.common.viewmodels.SearchViewModel$1"
    f = "SearchViewModel.kt"
    i = {}
    l = {}
    m = "invokeSuspend"
    n = {}
    nl = {}
    s = {}
    v = 0x2
.end annotation


# instance fields
.field synthetic L$0:Ljava/lang/Object;

.field label:I

.field final synthetic this$0:Lcom/stremio/common/viewmodels/SearchViewModel;


# direct methods
.method constructor <init>(Lcom/stremio/common/viewmodels/SearchViewModel;Lkotlin/coroutines/Continuation;)V
    .registers 3
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lcom/stremio/common/viewmodels/SearchViewModel;",
            "Lkotlin/coroutines/Continuation<",
            "-",
            "Lcom/stremio/common/viewmodels/SearchViewModel$1;",
            ">;)V"
        }
    .end annotation

    iput-object p1, p0, Lcom/stremio/common/viewmodels/SearchViewModel$1;->this$0:Lcom/stremio/common/viewmodels/SearchViewModel;

    const/4 p1, 0x2

    invoke-direct {p0, p1, p2}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .registers 4
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/Object;",
            "Lkotlin/coroutines/Continuation<",
            "*>;)",
            "Lkotlin/coroutines/Continuation<",
            "Lkotlin/Unit;",
            ">;"
        }
    .end annotation

    new-instance v0, Lcom/stremio/common/viewmodels/SearchViewModel$1;

    iget-object p0, p0, Lcom/stremio/common/viewmodels/SearchViewModel$1;->this$0:Lcom/stremio/common/viewmodels/SearchViewModel;

    invoke-direct {v0, p0, p2}, Lcom/stremio/common/viewmodels/SearchViewModel$1;-><init>(Lcom/stremio/common/viewmodels/SearchViewModel;Lkotlin/coroutines/Continuation;)V

    iput-object p1, v0, Lcom/stremio/common/viewmodels/SearchViewModel$1;->L$0:Ljava/lang/Object;

    check-cast v0, Lkotlin/coroutines/Continuation;

    return-object v0
.end method

.method public final invoke(Lcom/stremio/core/models/Ctx;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .registers 3
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lcom/stremio/core/models/Ctx;",
            "Lkotlin/coroutines/Continuation<",
            "-",
            "Lkotlin/Unit;",
            ">;)",
            "Ljava/lang/Object;"
        }
    .end annotation

    invoke-virtual {p0, p1, p2}, Lcom/stremio/common/viewmodels/SearchViewModel$1;->create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/stremio/common/viewmodels/SearchViewModel$1;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/stremio/common/viewmodels/SearchViewModel$1;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .registers 3

    check-cast p1, Lcom/stremio/core/models/Ctx;

    check-cast p2, Lkotlin/coroutines/Continuation;

    invoke-virtual {p0, p1, p2}, Lcom/stremio/common/viewmodels/SearchViewModel$1;->invoke(Lcom/stremio/core/models/Ctx;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .registers 4

    iget-object v0, p0, Lcom/stremio/common/viewmodels/SearchViewModel$1;->L$0:Ljava/lang/Object;

    check-cast v0, Lcom/stremio/core/models/Ctx;

    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    .line 35
    iget v1, p0, Lcom/stremio/common/viewmodels/SearchViewModel$1;->label:I

    if-nez v1, :cond_4b

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    .line 36
    iget-object p0, p0, Lcom/stremio/common/viewmodels/SearchViewModel$1;->this$0:Lcom/stremio/common/viewmodels/SearchViewModel;

    invoke-static {p0}, Lcom/stremio/common/viewmodels/SearchViewModel;->access$get_history$p(Lcom/stremio/common/viewmodels/SearchViewModel;)Lkotlinx/coroutines/flow/MutableStateFlow;

    move-result-object p0

    invoke-virtual {v0}, Lcom/stremio/core/models/Ctx;->getSearchHistory()Lcom/stremio/core/types/SearchHistoryBucket;

    move-result-object p1

    invoke-virtual {p1}, Lcom/stremio/core/types/SearchHistoryBucket;->getItems()Ljava/util/List;

    move-result-object p1

    check-cast p1, Ljava/lang/Iterable;

    .line 71
    new-instance v0, Ljava/util/ArrayList;

    const/16 v1, 0xa

    invoke-static {p1, v1}, Lkotlin/collections/CollectionsKt;->collectionSizeOrDefault(Ljava/lang/Iterable;I)I

    move-result v1

    invoke-direct {v0, v1}, Ljava/util/ArrayList;-><init>(I)V

    check-cast v0, Ljava/util/Collection;

    .line 72
    invoke-interface {p1}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object p1

    :goto_2f
    invoke-interface {p1}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_43

    invoke-interface {p1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    .line 73
    check-cast v1, Lcom/stremio/core/types/SearchHistory;

    .line 37
    invoke-virtual {v1}, Lcom/stremio/core/types/SearchHistory;->getQuery()Ljava/lang/String;

    move-result-object v1

    .line 73
    invoke-interface {v0, v1}, Ljava/util/Collection;->add(Ljava/lang/Object;)Z

    goto :goto_2f

    .line 74
    :cond_43
    check-cast v0, Ljava/util/List;

    .line 36
    invoke-interface {p0, v0}, Lkotlinx/coroutines/flow/MutableStateFlow;->setValue(Ljava/lang/Object;)V

    .line 38
    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0

    .line 35
    :cond_4b
    new-instance p0, Ljava/lang/IllegalStateException;

    const-string p1, "call to \'resume\' before \'invoke\' with coroutine"

    invoke-direct {p0, p1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw p0
.end method

###### Class com.stremio.common.viewmodels.SearchViewModel.AnonymousClass1 (com.stremio.common.viewmodels.SearchViewModel$search$1)
.class final Lcom/stremio/common/viewmodels/SearchViewModel$search$1;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "SearchViewModel.kt"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/stremio/common/viewmodels/SearchViewModel;->search(Ljava/lang/String;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x18
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lkotlin/coroutines/jvm/internal/SuspendLambda;",
        "Lkotlin/jvm/functions/Function2<",
        "Lcom/stremio/core/models/CatalogsWithExtra;",
        "Lkotlin/coroutines/Continuation<",
        "-",
        "Lkotlin/Unit;",
        ">;",
        "Ljava/lang/Object;",
        ">;"
    }
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000\u000c\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"
    }
    d2 = {
        "<anonymous>",
        "",
        "it",
        "Lcom/stremio/core/models/CatalogsWithExtra;"
    }
    k = 0x3
    mv = {
        0x2,
        0x4,
        0x0
    }
    xi = 0x530
.end annotation

.annotation runtime Lkotlin/coroutines/jvm/internal/DebugMetadata;
    c = "com.stremio.common.viewmodels.SearchViewModel$search$1"
    f = "SearchViewModel.kt"
    i = {}
    l = {}
    m = "invokeSuspend"
    n = {}
    nl = {}
    s = {}
    v = 0x2
.end annotation


# instance fields
.field synthetic L$0:Ljava/lang/Object;

.field label:I

.field final synthetic this$0:Lcom/stremio/common/viewmodels/SearchViewModel;


# direct methods
.method constructor <init>(Lcom/stremio/common/viewmodels/SearchViewModel;Lkotlin/coroutines/Continuation;)V
    .registers 3
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lcom/stremio/common/viewmodels/SearchViewModel;",
            "Lkotlin/coroutines/Continuation<",
            "-",
            "Lcom/stremio/common/viewmodels/SearchViewModel$search$1;",
            ">;)V"
        }
    .end annotation

    iput-object p1, p0, Lcom/stremio/common/viewmodels/SearchViewModel$search$1;->this$0:Lcom/stremio/common/viewmodels/SearchViewModel;

    const/4 p1, 0x2

    invoke-direct {p0, p1, p2}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .registers 4
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/Object;",
            "Lkotlin/coroutines/Continuation<",
            "*>;)",
            "Lkotlin/coroutines/Continuation<",
            "Lkotlin/Unit;",
            ">;"
        }
    .end annotation

    new-instance v0, Lcom/stremio/common/viewmodels/SearchViewModel$search$1;

    iget-object p0, p0, Lcom/stremio/common/viewmodels/SearchViewModel$search$1;->this$0:Lcom/stremio/common/viewmodels/SearchViewModel;

    invoke-direct {v0, p0, p2}, Lcom/stremio/common/viewmodels/SearchViewModel$search$1;-><init>(Lcom/stremio/common/viewmodels/SearchViewModel;Lkotlin/coroutines/Continuation;)V

    iput-object p1, v0, Lcom/stremio/common/viewmodels/SearchViewModel$search$1;->L$0:Ljava/lang/Object;

    check-cast v0, Lkotlin/coroutines/Continuation;

    return-object v0
.end method

.method public final invoke(Lcom/stremio/core/models/CatalogsWithExtra;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .registers 3
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lcom/stremio/core/models/CatalogsWithExtra;",
            "Lkotlin/coroutines/Continuation<",
            "-",
            "Lkotlin/Unit;",
            ">;)",
            "Ljava/lang/Object;"
        }
    .end annotation

    invoke-virtual {p0, p1, p2}, Lcom/stremio/common/viewmodels/SearchViewModel$search$1;->create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/stremio/common/viewmodels/SearchViewModel$search$1;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/stremio/common/viewmodels/SearchViewModel$search$1;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .registers 3

    check-cast p1, Lcom/stremio/core/models/CatalogsWithExtra;

    check-cast p2, Lkotlin/coroutines/Continuation;

    invoke-virtual {p0, p1, p2}, Lcom/stremio/common/viewmodels/SearchViewModel$search$1;->invoke(Lcom/stremio/core/models/CatalogsWithExtra;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .registers 4

    iget-object v0, p0, Lcom/stremio/common/viewmodels/SearchViewModel$search$1;->L$0:Ljava/lang/Object;

    check-cast v0, Lcom/stremio/core/models/CatalogsWithExtra;

    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    .line 46
    iget v1, p0, Lcom/stremio/common/viewmodels/SearchViewModel$search$1;->label:I

    if-nez v1, :cond_1e

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    iget-object p0, p0, Lcom/stremio/common/viewmodels/SearchViewModel$search$1;->this$0:Lcom/stremio/common/viewmodels/SearchViewModel;

    invoke-static {p0}, Lcom/stremio/common/viewmodels/SearchViewModel;->access$get_state$p(Lcom/stremio/common/viewmodels/SearchViewModel;)Lkotlinx/coroutines/flow/MutableStateFlow;

    move-result-object p0

    invoke-virtual {v0}, Lcom/stremio/core/models/CatalogsWithExtra;->getCatalogs()Ljava/util/List;

    move-result-object p1

    invoke-interface {p0, p1}, Lkotlinx/coroutines/flow/MutableStateFlow;->setValue(Ljava/lang/Object;)V

    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0

    :cond_1e
    new-instance p0, Ljava/lang/IllegalStateException;

    const-string p1, "call to \'resume\' before \'invoke\' with coroutine"

    invoke-direct {p0, p1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw p0
.end method

###### Class com.stremio.common.viewmodels.SearchViewModel.AnonymousClass1 (com.stremio.common.viewmodels.SearchViewModel$searchSuggestions$1)
.class final Lcom/stremio/common/viewmodels/SearchViewModel$searchSuggestions$1;
.super Lkotlin/coroutines/jvm/internal/SuspendLambda;
.source "SearchViewModel.kt"

# interfaces
.implements Lkotlin/jvm/functions/Function2;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/stremio/common/viewmodels/SearchViewModel;->searchSuggestions(Ljava/lang/String;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x18
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lkotlin/coroutines/jvm/internal/SuspendLambda;",
        "Lkotlin/jvm/functions/Function2<",
        "Lkotlinx/coroutines/CoroutineScope;",
        "Lkotlin/coroutines/Continuation<",
        "-",
        "Lkotlin/Unit;",
        ">;",
        "Ljava/lang/Object;",
        ">;"
    }
.end annotation

.annotation system Ldalvik/annotation/SourceDebugExtension;
    value = "SMAP\nSearchViewModel.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SearchViewModel.kt\ncom/stremio/common/viewmodels/SearchViewModel$searchSuggestions$1\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,70:1\n1745#2:71\n1820#2,3:72\n*S KotlinDebug\n*F\n+ 1 SearchViewModel.kt\ncom/stremio/common/viewmodels/SearchViewModel$searchSuggestions$1\n*L\n55#1:71\n55#1:72,3\n*E\n"
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"
    }
    d2 = {
        "<anonymous>",
        "",
        "Lkotlinx/coroutines/CoroutineScope;"
    }
    k = 0x3
    mv = {
        0x2,
        0x4,
        0x0
    }
    xi = 0x530
.end annotation

.annotation runtime Lkotlin/coroutines/jvm/internal/DebugMetadata;
    c = "com.stremio.common.viewmodels.SearchViewModel$searchSuggestions$1"
    f = "SearchViewModel.kt"
    i = {
        0x2
    }
    l = {
        0x35,
        0x36,
        0x37
    }
    m = "invokeSuspend"
    n = {
        "metas"
    }
    nl = {
        0x36,
        0x37,
        0x38
    }
    s = {
        "L$0"
    }
    v = 0x2
.end annotation


# instance fields
.field final synthetic $query:Ljava/lang/String;

.field L$0:Ljava/lang/Object;

.field label:I

.field final synthetic this$0:Lcom/stremio/common/viewmodels/SearchViewModel;


# direct methods
.method constructor <init>(Lcom/stremio/common/viewmodels/SearchViewModel;Ljava/lang/String;Lkotlin/coroutines/Continuation;)V
    .registers 4
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lcom/stremio/common/viewmodels/SearchViewModel;",
            "Ljava/lang/String;",
            "Lkotlin/coroutines/Continuation<",
            "-",
            "Lcom/stremio/common/viewmodels/SearchViewModel$searchSuggestions$1;",
            ">;)V"
        }
    .end annotation

    iput-object p1, p0, Lcom/stremio/common/viewmodels/SearchViewModel$searchSuggestions$1;->this$0:Lcom/stremio/common/viewmodels/SearchViewModel;

    iput-object p2, p0, Lcom/stremio/common/viewmodels/SearchViewModel$searchSuggestions$1;->$query:Ljava/lang/String;

    const/4 p1, 0x2

    invoke-direct {p0, p1, p3}, Lkotlin/coroutines/jvm/internal/SuspendLambda;-><init>(ILkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;
    .registers 4
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/Object;",
            "Lkotlin/coroutines/Continuation<",
            "*>;)",
            "Lkotlin/coroutines/Continuation<",
            "Lkotlin/Unit;",
            ">;"
        }
    .end annotation

    new-instance p1, Lcom/stremio/common/viewmodels/SearchViewModel$searchSuggestions$1;

    iget-object v0, p0, Lcom/stremio/common/viewmodels/SearchViewModel$searchSuggestions$1;->this$0:Lcom/stremio/common/viewmodels/SearchViewModel;

    iget-object p0, p0, Lcom/stremio/common/viewmodels/SearchViewModel$searchSuggestions$1;->$query:Ljava/lang/String;

    invoke-direct {p1, v0, p0, p2}, Lcom/stremio/common/viewmodels/SearchViewModel$searchSuggestions$1;-><init>(Lcom/stremio/common/viewmodels/SearchViewModel;Ljava/lang/String;Lkotlin/coroutines/Continuation;)V

    check-cast p1, Lkotlin/coroutines/Continuation;

    return-object p1
.end method

.method public bridge synthetic invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .registers 3

    check-cast p1, Lkotlinx/coroutines/CoroutineScope;

    check-cast p2, Lkotlin/coroutines/Continuation;

    invoke-virtual {p0, p1, p2}, Lcom/stremio/common/viewmodels/SearchViewModel$searchSuggestions$1;->invoke(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invoke(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .registers 3
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lkotlinx/coroutines/CoroutineScope;",
            "Lkotlin/coroutines/Continuation<",
            "-",
            "Lkotlin/Unit;",
            ">;)",
            "Ljava/lang/Object;"
        }
    .end annotation

    invoke-virtual {p0, p1, p2}, Lcom/stremio/common/viewmodels/SearchViewModel$searchSuggestions$1;->create(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;

    move-result-object p0

    check-cast p0, Lcom/stremio/common/viewmodels/SearchViewModel$searchSuggestions$1;

    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    invoke-virtual {p0, p1}, Lcom/stremio/common/viewmodels/SearchViewModel$searchSuggestions$1;->invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .registers 8

    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v0

    .line 52
    iget v1, p0, Lcom/stremio/common/viewmodels/SearchViewModel$searchSuggestions$1;->label:I

    const/4 v2, 0x3

    const/4 v3, 0x2

    const/4 v4, 0x1

    if-eqz v1, :cond_2a

    if-eq v1, v4, :cond_26

    if-eq v1, v3, :cond_22

    if-ne v1, v2, :cond_1a

    iget-object p0, p0, Lcom/stremio/common/viewmodels/SearchViewModel$searchSuggestions$1;->L$0:Ljava/lang/Object;

    check-cast p0, Ljava/util/List;

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto/16 :goto_99

    :cond_1a
    new-instance p0, Ljava/lang/IllegalStateException;

    const-string p1, "call to \'resume\' before \'invoke\' with coroutine"

    invoke-direct {p0, p1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw p0

    :cond_22
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto :goto_4f

    :cond_26
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto :goto_3b

    :cond_2a
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    .line 53
    move-object p1, p0

    check-cast p1, Lkotlin/coroutines/Continuation;

    iput v4, p0, Lcom/stremio/common/viewmodels/SearchViewModel$searchSuggestions$1;->label:I

    const-wide/16 v4, 0xfa

    invoke-static {v4, v5, p1}, Lkotlinx/coroutines/DelayKt;->delay(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-ne p1, v0, :cond_3b

    goto :goto_98

    .line 54
    :cond_3b
    :goto_3b
    iget-object p1, p0, Lcom/stremio/common/viewmodels/SearchViewModel$searchSuggestions$1;->this$0:Lcom/stremio/common/viewmodels/SearchViewModel;

    invoke-static {p1}, Lcom/stremio/common/viewmodels/SearchViewModel;->access$getStremioApi$p(Lcom/stremio/common/viewmodels/SearchViewModel;)Lcom/stremio/common/api/StremioApi;

    move-result-object p1

    iget-object v1, p0, Lcom/stremio/common/viewmodels/SearchViewModel$searchSuggestions$1;->$query:Ljava/lang/String;

    move-object v4, p0

    check-cast v4, Lkotlin/coroutines/Continuation;

    iput v3, p0, Lcom/stremio/common/viewmodels/SearchViewModel$searchSuggestions$1;->label:I

    invoke-virtual {p1, v1, v4}, Lcom/stremio/common/api/StremioApi;->quickSearch(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-ne p1, v0, :cond_4f

    goto :goto_98

    .line 52
    :cond_4f
    :goto_4f
    check-cast p1, Ljava/util/List;

    .line 55
    iget-object v1, p0, Lcom/stremio/common/viewmodels/SearchViewModel$searchSuggestions$1;->this$0:Lcom/stremio/common/viewmodels/SearchViewModel;

    invoke-static {v1}, Lcom/stremio/common/viewmodels/SearchViewModel;->access$get_suggestions$p(Lcom/stremio/common/viewmodels/SearchViewModel;)Lkotlinx/coroutines/flow/MutableSharedFlow;

    move-result-object v1

    move-object v3, p1

    check-cast v3, Ljava/lang/Iterable;

    .line 71
    new-instance v4, Ljava/util/ArrayList;

    const/16 v5, 0xa

    invoke-static {v3, v5}, Lkotlin/collections/CollectionsKt;->collectionSizeOrDefault(Ljava/lang/Iterable;I)I

    move-result v5

    invoke-direct {v4, v5}, Ljava/util/ArrayList;-><init>(I)V

    check-cast v4, Ljava/util/Collection;

    .line 72
    invoke-interface {v3}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object v3

    :goto_6b
    invoke-interface {v3}, Ljava/util/Iterator;->hasNext()Z

    move-result v5

    if-eqz v5, :cond_7f

    invoke-interface {v3}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v5

    .line 73
    check-cast v5, Lcom/stremio/core/types/resource/MetaItemPreview;

    .line 55
    invoke-virtual {v5}, Lcom/stremio/core/types/resource/MetaItemPreview;->getName()Ljava/lang/String;

    move-result-object v5

    .line 73
    invoke-interface {v4, v5}, Ljava/util/Collection;->add(Ljava/lang/Object;)Z

    goto :goto_6b

    .line 74
    :cond_7f
    check-cast v4, Ljava/util/List;

    .line 71
    check-cast v4, Ljava/lang/Iterable;

    .line 55
    invoke-static {v4}, Lkotlin/collections/CollectionsKt;->distinct(Ljava/lang/Iterable;)Ljava/util/List;

    move-result-object v3

    move-object v4, p0

    check-cast v4, Lkotlin/coroutines/Continuation;

    invoke-static {p1}, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p1

    iput-object p1, p0, Lcom/stremio/common/viewmodels/SearchViewModel$searchSuggestions$1;->L$0:Ljava/lang/Object;

    iput v2, p0, Lcom/stremio/common/viewmodels/SearchViewModel$searchSuggestions$1;->label:I

    invoke-interface {v1, v3, v4}, Lkotlinx/coroutines/flow/MutableSharedFlow;->emit(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    if-ne p0, v0, :cond_99

    :goto_98
    return-object v0

    .line 56
    :cond_99
    :goto_99
    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0
.end method
