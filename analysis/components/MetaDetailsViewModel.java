// [jadx java-decompile failed → smali]
// target: com.stremio.common.viewmodels.MetaDetailsViewModel
// markers: Code decompiled incorrectly, please refer to instructions dump
// (Java body was an anti-decompile stub; showing smali. Pass smali_fallback=false for the partial Java.)

###### Class com.stremio.common.viewmodels.MetaDetailsViewModel (com.stremio.common.viewmodels.MetaDetailsViewModel)
.class public final Lcom/stremio/common/viewmodels/MetaDetailsViewModel;
.super Landroidx/lifecycle/ViewModel;
.source "MetaDetailsViewModel.kt"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/stremio/common/viewmodels/MetaDetailsViewModel$AddonStreams;,
        Lcom/stremio/common/viewmodels/MetaDetailsViewModel$Companion;
    }
.end annotation

.annotation system Ldalvik/annotation/SourceDebugExtension;
    value = "SMAP\nMetaDetailsViewModel.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MetaDetailsViewModel.kt\ncom/stremio/common/viewmodels/MetaDetailsViewModel\n+ 2 Transform.kt\nkotlinx/coroutines/flow/FlowKt__TransformKt\n+ 3 Emitters.kt\nkotlinx/coroutines/flow/FlowKt__EmittersKt\n+ 4 SafeCollector.common.kt\nkotlinx/coroutines/flow/internal/SafeCollector_commonKt\n+ 5 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 6 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,396:1\n49#2:397\n51#2:401\n17#2:402\n19#2:406\n17#2:407\n19#2:411\n45#3:398\n49#3:400\n45#3:403\n49#3:405\n45#3:408\n49#3:410\n105#4:399\n105#4:404\n105#4:409\n1#5:412\n1#5:432\n1855#6,8:413\n1801#6,10:421\n2199#6:431\n2200#6:433\n1811#6:434\n363#6,7:435\n1550#6:442\n1639#6,5:443\n777#6:448\n873#6,2:449\n1755#6:451\n1788#6,4:452\n777#6:456\n873#6,2:457\n1745#6:459\n1820#6,3:460\n1550#6:463\n1639#6,5:464\n1745#6:469\n1820#6,3:470\n2329#6,14:473\n*S KotlinDebug\n*F\n+ 1 MetaDetailsViewModel.kt\ncom/stremio/common/viewmodels/MetaDetailsViewModel\n*L\n66#1:397\n66#1:401\n72#1:402\n72#1:406\n145#1:407\n145#1:411\n66#1:398\n66#1:400\n72#1:403\n72#1:405\n145#1:408\n145#1:410\n66#1:399\n72#1:404\n145#1:409\n172#1:432\n170#1:413,8\n172#1:421,10\n172#1:431\n172#1:433\n172#1:434\n224#1:435,7\n339#1:442\n339#1:443,5\n343#1:448\n343#1:449,2\n344#1:451\n344#1:452,4\n361#1:456\n361#1:457,2\n361#1:459\n361#1:460,3\n364#1:463\n364#1:464,5\n382#1:469\n382#1:470,3\n383#1:473,14\n*E\n"
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000\u00c0\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0010\u000e\n\u0002\u0008\u0003\n\u0002\u0010\u000b\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0010\t\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0002\u0008\t\n\u0002\u0018\u0002\n\u0002\u0008\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0004\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0000\u0008\u0007\u0018\u0000 O2\u00020\u0001:\u0002OPB\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0004\u0008\u0006\u0010\u0007J\u000e\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 J*\u0010!\u001a\u00020\u001e2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020#2\u0008\u0010%\u001a\u0004\u0018\u00010#2\u0006\u0010&\u001a\u00020\'H\u0002J\u0010\u0010(\u001a\u00020\u001e2\u0006\u0010)\u001a\u00020*H\u0002J\u0012\u0010+\u001a\u00020\u001e2\u0008\u0010,\u001a\u0004\u0018\u00010-H\u0002J\u0010\u0010.\u001a\u00020\u001e2\u0006\u0010/\u001a\u000200H\u0002J\u0010\u00101\u001a\u00020\u001e2\u0006\u00102\u001a\u000203H\u0002J\u0008\u00104\u001a\u00020\u001eH\u0002J\u0008\u00105\u001a\u00020\u001eH\u0002J\u0012\u00106\u001a\u00020\u001e2\u0008\u00107\u001a\u0004\u0018\u00010-H\u0002J\u001a\u00108\u001a\u00020\u001e2\u0008\u00107\u001a\u0004\u0018\u00010-2\u0006\u00109\u001a\u00020\'H\u0002J\u0008\u0010:\u001a\u00020\u001eH\u0002J\u0012\u0010;\u001a\u00020\u001e2\u0008\u0010<\u001a\u0004\u0018\u00010=H\u0002J\n\u0010>\u001a\u0004\u0018\u00010-H\u0002J\u0008\u0010?\u001a\u00020\u001eH\u0002J\u0012\u0010@\u001a\u00020\'2\u0008\u0010%\u001a\u0004\u0018\u00010#H\u0002J\u0012\u0010A\u001a\u0004\u0018\u00010\u00172\u0006\u0010)\u001a\u00020*H\u0002J\u001c\u0010B\u001a\u0008\u0012\u0004\u0012\u00020D0C2\u000c\u0010E\u001a\u0008\u0012\u0004\u0012\u00020F0CH\u0002J\u0018\u0010G\u001a\u0004\u0018\u00010#2\u000c\u0010H\u001a\u0008\u0012\u0004\u0012\u00020F0CH\u0002J\u0012\u0010I\u001a\u00020#2\u0008\u0010J\u001a\u0004\u0018\u00010KH\u0002J\u0012\u0010L\u001a\u0004\u0018\u00010M2\u0006\u0010)\u001a\u00020*H\u0002J\u0008\u0010N\u001a\u00020\u001eH\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0008\u001a\u0008\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u000b\u001a\u0008\u0012\u0004\u0012\u00020\n0\u000c\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008\r\u0010\u000eR\u0018\u0010\u000f\u001a\u000c\u0012\u0008\u0012\u0006\u0012\u0002\u0008\u00030\u00110\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0012\u001a\u000c\u0012\u0008\u0012\u0006\u0012\u0002\u0008\u00030\u00110\u0013\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008\u0014\u0010\u0015R\u0016\u0010\u0016\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00170\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u0018\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00170\u000c\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008\u0019\u0010\u000eR\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001bX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00ca\u0001\u0010\u0008R\u0012\u000c\u0008S\u0012\u0008\u0008\u000cJ\u0004\u0008\u0008(T\u00ca\u0001\u000c\u0008U\u0012\u0008\u0008V\u0012\u0004\u0008\u0003\u0010\u0000\u00a8\u0006Q"
    }
    d2 = {
        "Lcom/stremio/common/viewmodels/MetaDetailsViewModel;",
        "Landroidx/lifecycle/ViewModel;",
        "context",
        "Landroid/content/Context;",
        "stremioApi",
        "Lcom/stremio/common/api/StremioApi;",
        "<init>",
        "(Landroid/content/Context;Lcom/stremio/common/api/StremioApi;)V",
        "_state",
        "Lkotlinx/coroutines/flow/MutableStateFlow;",
        "Lcom/stremio/common/viewmodels/state/MetaDetailsState;",
        "state",
        "Lkotlinx/coroutines/flow/StateFlow;",
        "getState",
        "()Lkotlinx/coroutines/flow/StateFlow;",
        "_events",
        "Lkotlinx/coroutines/flow/MutableSharedFlow;",
        "Lcom/stremio/core/runtime/msg/Event$Type;",
        "events",
        "Lkotlinx/coroutines/flow/SharedFlow;",
        "getEvents",
        "()Lkotlinx/coroutines/flow/SharedFlow;",
        "_autoPlayStream",
        "Lcom/stremio/core/types/resource/Stream;",
        "autoPlayStream",
        "getAutoPlayStream",
        "loadMetaJob",
        "Lkotlinx/coroutines/Job;",
        "autoPlayTimeoutJob",
        "onEvent",
        "",
        "event",
        "Lcom/stremio/common/viewmodels/state/MetaDetailsEvent;",
        "loadMetaDetails",
        "type",
        "",
        "id",
        "videoId",
        "autoPlay",
        "",
        "updateMetaDetails",
        "details",
        "Lcom/stremio/core/models/MetaDetails;",
        "selectVideo",
        "selectedVideo",
        "Lcom/stremio/core/types/resource/Video;",
        "selectSeason",
        "season",
        "",
        "selectAddon",
        "addon",
        "Lcom/stremio/common/viewmodels/state/Addon;",
        "clearStreams",
        "toggleInLibrary",
        "toggleVideoWatched",
        "video",
        "toggleSeasonWatched",
        "watched",
        "toggleNotifications",
        "rate",
        "rating",
        "Lstremio/core/types/Rating;",
        "defaultFocusedVideo",
        "tryAutoPlayStream",
        "canShowStreamList",
        "getSuggestedStream",
        "addonsStream",
        "",
        "Lcom/stremio/common/viewmodels/MetaDetailsViewModel$AddonStreams;",
        "loadableStreams",
        "Lcom/stremio/core/models/LoadableStreams;",
        "streamsLabel",
        "streams",
        "longestDescription",
        "meta",
        "Lcom/stremio/core/types/resource/MetaItem;",
        "ratingInfo",
        "Lstremio/core/types/RatingInfo;",
        "onCleared",
        "Companion",
        "AddonStreams",
        "Stremio:common_release",
        "Landroid/annotation/SuppressLint;",
        "value",
        "StaticFieldLeak",
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
.field public static final $stable:I

.field private static final AUTO_PLAY_TIMEOUT_MS:J = 0x2710L

.field public static final Companion:Lcom/stremio/common/viewmodels/MetaDetailsViewModel$Companion;

.field public static final NO_INDEX:I = -0x1


# instance fields
.field private final _autoPlayStream:Lkotlinx/coroutines/flow/MutableStateFlow;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lkotlinx/coroutines/flow/MutableStateFlow<",
            "Lcom/stremio/core/types/resource/Stream;",
            ">;"
        }
    .end annotation
.end field

.field private final _events:Lkotlinx/coroutines/flow/MutableSharedFlow;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lkotlinx/coroutines/flow/MutableSharedFlow<",
            "Lcom/stremio/core/runtime/msg/Event$Type<",
            "*>;>;"
        }
    .end annotation
.end field

.field private final _state:Lkotlinx/coroutines/flow/MutableStateFlow;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lkotlinx/coroutines/flow/MutableStateFlow<",
            "Lcom/stremio/common/viewmodels/state/MetaDetailsState;",
            ">;"
        }
    .end annotation
.end field

.field private final autoPlayStream:Lkotlinx/coroutines/flow/StateFlow;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lkotlinx/coroutines/flow/StateFlow<",
            "Lcom/stremio/core/types/resource/Stream;",
            ">;"
        }
    .end annotation
.end field

.field private autoPlayTimeoutJob:Lkotlinx/coroutines/Job;

.field private final context:Landroid/content/Context;

.field private final events:Lkotlinx/coroutines/flow/SharedFlow;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lkotlinx/coroutines/flow/SharedFlow<",
            "Lcom/stremio/core/runtime/msg/Event$Type<",
            "*>;>;"
        }
    .end annotation
.end field

.field private loadMetaJob:Lkotlinx/coroutines/Job;

.field private final state:Lkotlinx/coroutines/flow/StateFlow;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lkotlinx/coroutines/flow/StateFlow<",
            "Lcom/stremio/common/viewmodels/state/MetaDetailsState;",
            ">;"
        }
    .end annotation
.end field

.field private final stremioApi:Lcom/stremio/common/api/StremioApi;


# direct methods
.method static constructor <clinit>()V
    .registers 2

    new-instance v0, Lcom/stremio/common/viewmodels/MetaDetailsViewModel$Companion;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lcom/stremio/common/viewmodels/MetaDetailsViewModel$Companion;-><init>(Lkotlin/jvm/internal/DefaultConstructorMarker;)V

    sput-object v0, Lcom/stremio/common/viewmodels/MetaDetailsViewModel;->Companion:Lcom/stremio/common/viewmodels/MetaDetailsViewModel$Companion;

    const/16 v0, 0x8

    sput v0, Lcom/stremio/common/viewmodels/MetaDetailsViewModel;->$stable:I

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Lcom/stremio/common/api/StremioApi;)V
    .registers 34

    move-object/from16 v0, p0

    move-object/from16 v1, p1

    move-object/from16 v2, p2

    const-string v3, "context"

    invoke-static {v1, v3}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    const-string v3, "stremioApi"

    invoke-static {v2, v3}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 38
    invoke-direct {v0}, Landroidx/lifecycle/ViewModel;-><init>()V

    .line 40
    iput-object v1, v0, Lcom/stremio/common/viewmodels/MetaDetailsViewModel;->context:Landroid/content/Context;

    .line 41
    iput-object v2, v0, Lcom/stremio/common/viewmodels/MetaDetailsViewModel;->stremioApi:Lcom/stremio/common/api/StremioApi;

    .line 53
    new-instance v4, Lcom/stremio/common/viewmodels/state/MetaDetailsState;

    const v29, 0xffffff

    const/16 v30, 0x0

    const/4 v5, 0x0

    const/4 v6, 0x0

    const/4 v7, 0x0

    const/4 v8, 0x0

    const/4 v9, 0x0

    const/4 v10, 0x0

    const/4 v11, 0x0

    const/4 v12, 0x0

    const/4 v13, 0x0

    const/4 v14, 0x0

    const/4 v15, 0x0

    const/16 v16, 0x0

    const/16 v17, 0x0

    const/16 v18, 0x0

    const/16 v19, 0x0

    const/16 v20, 0x0

    const/16 v21, 0x0

    const/16 v22, 0x0

    const/16 v23, 0x0

    const/16 v24, 0x0

    const/16 v25, 0x0

    const/16 v26, 0x0

    const/16 v27, 0x0

    const/16 v28, 0x0

    invoke-direct/range {v4 .. v30}, Lcom/stremio/common/viewmodels/state/MetaDetailsState;-><init>(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLcom/stremio/core/types/resource/MetaItem;Lcom/stremio/core/types/resource/Video;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/lang/String;ZLjava/lang/String;Lcom/stremio/core/types/resource/Stream;Ljava/lang/String;IIILcom/stremio/core/types/resource/Stream;ZZZZLstremio/core/types/RatingInfo;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    invoke-static {v4}, Lkotlinx/coroutines/flow/StateFlowKt;->MutableStateFlow(Ljava/lang/Object;)Lkotlinx/coroutines/flow/MutableStateFlow;

    move-result-object v1

    iput-object v1, v0, Lcom/stremio/common/viewmodels/MetaDetailsViewModel;->_state:Lkotlinx/coroutines/flow/MutableStateFlow;

    .line 54
    invoke-static {v1}, Lkotlinx/coroutines/flow/FlowKt;->asStateFlow(Lkotlinx/coroutines/flow/MutableStateFlow;)Lkotlinx/coroutines/flow/StateFlow;

    move-result-object v1

    iput-object v1, v0, Lcom/stremio/common/viewmodels/MetaDetailsViewModel;->state:Lkotlinx/coroutines/flow/StateFlow;

    const/4 v1, 0x7

    const/4 v3, 0x0

    const/4 v4, 0x0

    .line 56
    invoke-static {v3, v3, v4, v1, v4}, Lkotlinx/coroutines/flow/SharedFlowKt;->MutableSharedFlow$default(IILkotlinx/coroutines/channels/BufferOverflow;ILjava/lang/Object;)Lkotlinx/coroutines/flow/MutableSharedFlow;

    move-result-object v1

    iput-object v1, v0, Lcom/stremio/common/viewmodels/MetaDetailsViewModel;->_events:Lkotlinx/coroutines/flow/MutableSharedFlow;

    .line 57
    invoke-static {v1}, Lkotlinx/coroutines/flow/FlowKt;->asSharedFlow(Lkotlinx/coroutines/flow/MutableSharedFlow;)Lkotlinx/coroutines/flow/SharedFlow;

    move-result-object v1

    iput-object v1, v0, Lcom/stremio/common/viewmodels/MetaDetailsViewModel;->events:Lkotlinx/coroutines/flow/SharedFlow;

    .line 59
    invoke-static {v4}, Lkotlinx/coroutines/flow/StateFlowKt;->MutableStateFlow(Ljava/lang/Object;)Lkotlinx/coroutines/flow/MutableStateFlow;

    move-result-object v1

    iput-object v1, v0, Lcom/stremio/common/viewmodels/MetaDetailsViewModel;->_autoPlayStream:Lkotlinx/coroutines/flow/MutableStateFlow;

    .line 60
    invoke-static {v1}, Lkotlinx/coroutines/flow/FlowKt;->asStateFlow(Lkotlinx/coroutines/flow/MutableStateFlow;)Lkotlinx/coroutines/flow/StateFlow;

    move-result-object v1

    iput-object v1, v0, Lcom/stremio/common/viewmodels/MetaDetailsViewModel;->autoPlayStream:Lkotlinx/coroutines/flow/StateFlow;

    .line 65
    invoke-virtual {v2}, Lcom/stremio/common/api/StremioApi;->ctx()Lkotlinx/coroutines/flow/Flow;

    move-result-object v1

    .line 399
    new-instance v3, Lcom/stremio/common/viewmodels/MetaDetailsViewModel$special$$inlined$map$1;

    invoke-direct {v3, v1}, Lcom/stremio/common/viewmodels/MetaDetailsViewModel$special$$inlined$map$1;-><init>(Lkotlinx/coroutines/flow/Flow;)V

    check-cast v3, Lkotlinx/coroutines/flow/Flow;

    .line 67
    new-instance v1, Lcom/stremio/common/viewmodels/MetaDetailsViewModel$2;

    invoke-direct {v1, v0, v4}, Lcom/stremio/common/viewmodels/MetaDetailsViewModel$2;-><init>(Lcom/stremio/common/viewmodels/MetaDetailsViewModel;Lkotlin/coroutines/Continuation;)V

    check-cast v1, Lkotlin/jvm/functions/Function2;

    invoke-static {v3, v1}, Lkotlinx/coroutines/flow/FlowKt;->onEach(Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/flow/Flow;

    move-result-object v1

    .line 68
    move-object v3, v0

    check-cast v3, Landroidx/lifecycle/ViewModel;

    invoke-static {v3}, Landroidx/lifecycle/ViewModelKt;->getViewModelScope(Landroidx/lifecycle/ViewModel;)Lkotlinx/coroutines/CoroutineScope;

    move-result-object v5

    invoke-static {v1, v5}, Lkotlinx/coroutines/flow/FlowKt;->launchIn(Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/CoroutineScope;)Lkotlinx/coroutines/Job;

    .line 71
    invoke-virtual {v2}, Lcom/stremio/common/api/StremioApi;->coreEvents()Lkotlinx/coroutines/flow/Flow;

    move-result-object v1

    .line 404
    new-instance v2, Lcom/stremio/common/viewmodels/MetaDetailsViewModel$special$$inlined$filter$1;

    invoke-direct {v2, v1}, Lcom/stremio/common/viewmodels/MetaDetailsViewModel$special$$inlined$filter$1;-><init>(Lkotlinx/coroutines/flow/Flow;)V

    check-cast v2, Lkotlinx/coroutines/flow/Flow;

    .line 73
    new-instance v1, Lcom/stremio/common/viewmodels/MetaDetailsViewModel$4;

    invoke-direct {v1, v0, v4}, Lcom/stremio/common/viewmodels/MetaDetailsViewModel$4;-><init>(Lcom/stremio/common/viewmodels/MetaDetailsViewModel;Lkotlin/coroutines/Continuation;)V

    check-cast v1, Lkotlin/jvm/functions/Function2;

    invoke-static {v2, v1}, Lkotlinx/coroutines/flow/FlowKt;->onEach(Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/flow/Flow;

    move-result-object v0

    .line 74
    invoke-static {v3}, Landroidx/lifecycle/ViewModelKt;->getViewModelScope(Landroidx/lifecycle/ViewModel;)Lkotlinx/coroutines/CoroutineScope;

    move-result-object v1

    invoke-static {v0, v1}, Lkotlinx/coroutines/flow/FlowKt;->launchIn(Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/CoroutineScope;)Lkotlinx/coroutines/Job;

    return-void
.end method

.method public static final synthetic access$getStremioApi$p(Lcom/stremio/common/viewmodels/MetaDetailsViewModel;)Lcom/stremio/common/api/StremioApi;
    .registers 1

    .line 38
    iget-object p0, p0, Lcom/stremio/common/viewmodels/MetaDetailsViewModel;->stremioApi:Lcom/stremio/common/api/StremioApi;

    return-object p0
.end method

.method public static final synthetic access$get_events$p(Lcom/stremio/common/viewmodels/MetaDetailsViewModel;)Lkotlinx/coroutines/flow/MutableSharedFlow;
    .registers 1

    .line 38
    iget-object p0, p0, Lcom/stremio/common/viewmodels/MetaDetailsViewModel;->_events:Lkotlinx/coroutines/flow/MutableSharedFlow;

    return-object p0
.end method

.method public static final synthetic access$get_state$p(Lcom/stremio/common/viewmodels/MetaDetailsViewModel;)Lkotlinx/coroutines/flow/MutableStateFlow;
    .registers 1

    .line 38
    iget-object p0, p0, Lcom/stremio/common/viewmodels/MetaDetailsViewModel;->_state:Lkotlinx/coroutines/flow/MutableStateFlow;

    return-object p0
.end method

.method public static final synthetic access$loadMetaDetails(Lcom/stremio/common/viewmodels/MetaDetailsViewModel;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)V
    .registers 5

    .line 38
    invoke-direct {p0, p1, p2, p3, p4}, Lcom/stremio/common/viewmodels/MetaDetailsViewModel;->loadMetaDetails(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)V

    return-void
.end method

.method public static final synthetic access$updateMetaDetails(Lcom/stremio/common/viewmodels/MetaDetailsViewModel;Lcom/stremio/core/models/MetaDetails;)V
    .registers 2

    .line 38
    invoke-direct {p0, p1}, Lcom/stremio/common/viewmodels/MetaDetailsViewModel;->updateMetaDetails(Lcom/stremio/core/models/MetaDetails;)V

    return-void
.end method

.method private final addonsStream(Ljava/util/List;)Ljava/util/List;
    .registers 10
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "Lcom/stremio/core/models/LoadableStreams;",
            ">;)",
            "Ljava/util/List<",
            "Lcom/stremio/common/viewmodels/MetaDetailsViewModel$AddonStreams;",
            ">;"
        }
    .end annotation

    .line 334
    new-instance v0, Lcom/stremio/common/viewmodels/state/Addon;

    .line 337
    iget-object p0, p0, Lcom/stremio/common/viewmodels/MetaDetailsViewModel;->context:Landroid/content/Context;

    sget v1, Lcom/stremio/common/R$string;->label_all:I

    invoke-virtual {p0, v1}, Landroid/content/Context;->getString(I)Ljava/lang/String;

    move-result-object p0

    const-string v1, "getString(...)"

    invoke-static {p0, v1}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullExpressionValue(Ljava/lang/Object;Ljava/lang/String;)V

    const/4 v1, -0x1

    .line 334
    const-string v2, ""

    invoke-direct {v0, v1, v2, p0}, Lcom/stremio/common/viewmodels/state/Addon;-><init>(ILjava/lang/String;Ljava/lang/String;)V

    .line 339
    check-cast p1, Ljava/lang/Iterable;

    .line 442
    new-instance p0, Ljava/util/ArrayList;

    invoke-direct {p0}, Ljava/util/ArrayList;-><init>()V

    check-cast p0, Ljava/util/Collection;

    .line 443
    invoke-interface {p1}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :goto_22
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_38

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    .line 444
    check-cast v2, Lcom/stremio/core/models/LoadableStreams;

    .line 339
    invoke-static {v2}, Lcom/stremio/common/extensions/LoadableExtKt;->getAsReady(Lcom/stremio/core/models/LoadableStreams;)Ljava/util/List;

    move-result-object v2

    check-cast v2, Ljava/lang/Iterable;

    .line 445
    invoke-static {p0, v2}, Lkotlin/collections/CollectionsKt;->addAll(Ljava/util/Collection;Ljava/lang/Iterable;)Z

    goto :goto_22

    .line 447
    :cond_38
    check-cast p0, Ljava/util/List;

    .line 442
    check-cast p0, Ljava/lang/Iterable;

    .line 339
    invoke-static {p0}, Lkotlin/collections/CollectionsKt;->distinct(Ljava/lang/Iterable;)Ljava/util/List;

    move-result-object p0

    .line 333
    new-instance v1, Lcom/stremio/common/viewmodels/MetaDetailsViewModel$AddonStreams;

    invoke-direct {v1, v0, p0}, Lcom/stremio/common/viewmodels/MetaDetailsViewModel$AddonStreams;-><init>(Lcom/stremio/common/viewmodels/state/Addon;Ljava/util/List;)V

    .line 332
    invoke-static {v1}, Lkotlin/collections/CollectionsKt;->listOf(Ljava/lang/Object;)Ljava/util/List;

    move-result-object p0

    .line 448
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    check-cast v0, Ljava/util/Collection;

    .line 449
    invoke-interface {p1}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object p1

    :cond_54
    :goto_54
    invoke-interface {p1}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_6b

    invoke-interface {p1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    move-object v2, v1

    check-cast v2, Lcom/stremio/core/models/LoadableStreams;

    .line 343
    invoke-static {v2}, Lcom/stremio/common/extensions/LoadableExtKt;->isReady(Lcom/stremio/core/models/LoadableStreams;)Z

    move-result v2

    if-eqz v2, :cond_54

    .line 449
    invoke-interface {v0, v1}, Ljava/util/Collection;->add(Ljava/lang/Object;)Z

    goto :goto_54

    .line 450
    :cond_6b
    check-cast v0, Ljava/util/List;

    .line 448
    check-cast v0, Ljava/lang/Iterable;

    .line 451
    new-instance p1, Ljava/util/ArrayList;

    const/16 v1, 0xa

    invoke-static {v0, v1}, Lkotlin/collections/CollectionsKt;->collectionSizeOrDefault(Ljava/lang/Iterable;I)I

    move-result v1

    invoke-direct {p1, v1}, Ljava/util/ArrayList;-><init>(I)V

    check-cast p1, Ljava/util/Collection;

    .line 453
    invoke-interface {v0}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object v0

    const/4 v1, 0x0

    :goto_81
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_b9

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    add-int/lit8 v3, v1, 0x1

    if-gez v1, :cond_92

    .line 454
    invoke-static {}, Lkotlin/collections/CollectionsKt;->throwIndexOverflow()V

    :cond_92
    check-cast v2, Lcom/stremio/core/models/LoadableStreams;

    .line 345
    new-instance v4, Lcom/stremio/common/viewmodels/MetaDetailsViewModel$AddonStreams;

    .line 346
    new-instance v5, Lcom/stremio/common/viewmodels/state/Addon;

    .line 348
    invoke-virtual {v2}, Lcom/stremio/core/models/LoadableStreams;->getRequest()Lcom/stremio/core/types/addon/ResourceRequest;

    move-result-object v6

    invoke-virtual {v6}, Lcom/stremio/core/types/addon/ResourceRequest;->getBase()Ljava/lang/String;

    move-result-object v6

    .line 349
    invoke-virtual {v2}, Lcom/stremio/core/models/LoadableStreams;->getTitle()Ljava/lang/String;

    move-result-object v7

    .line 346
    invoke-direct {v5, v1, v6, v7}, Lcom/stremio/common/viewmodels/state/Addon;-><init>(ILjava/lang/String;Ljava/lang/String;)V

    .line 351
    invoke-static {v2}, Lcom/stremio/common/extensions/LoadableExtKt;->getAsReady(Lcom/stremio/core/models/LoadableStreams;)Ljava/util/List;

    move-result-object v1

    check-cast v1, Ljava/lang/Iterable;

    invoke-static {v1}, Lkotlin/collections/CollectionsKt;->distinct(Ljava/lang/Iterable;)Ljava/util/List;

    move-result-object v1

    .line 345
    invoke-direct {v4, v5, v1}, Lcom/stremio/common/viewmodels/MetaDetailsViewModel$AddonStreams;-><init>(Lcom/stremio/common/viewmodels/state/Addon;Ljava/util/List;)V

    .line 454
    invoke-interface {p1, v4}, Ljava/util/Collection;->add(Ljava/lang/Object;)Z

    move v1, v3

    goto :goto_81

    .line 455
    :cond_b9
    check-cast p1, Ljava/util/List;

    .line 354
    check-cast p0, Ljava/util/Collection;

    check-cast p1, Ljava/lang/Iterable;

    invoke-static {p0, p1}, Lkotlin/collections/CollectionsKt;->plus(Ljava/util/Collection;Ljava/lang/Iterable;)Ljava/util/List;

    move-result-object p0

    return-object p0
.end method

.method private final canShowStreamList(Ljava/lang/String;)Z
    .registers 8

    const/4 v0, 0x1

    const/4 v1, 0x0

    if-eqz p1, :cond_6

    move v2, v0

    goto :goto_7

    :cond_6
    move v2, v1

    .line 314
    :goto_7
    iget-object p0, p0, Lcom/stremio/common/viewmodels/MetaDetailsViewModel;->state:Lkotlinx/coroutines/flow/StateFlow;

    invoke-interface {p0}, Lkotlinx/coroutines/flow/StateFlow;->getValue()Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lcom/stremio/common/viewmodels/state/MetaDetailsState;

    invoke-virtual {p0}, Lcom/stremio/common/viewmodels/state/MetaDetailsState;->getMeta()Lcom/stremio/core/types/resource/MetaItem;

    move-result-object p0

    const/4 v3, 0x0

    if-eqz p0, :cond_3e

    invoke-virtual {p0}, Lcom/stremio/core/types/resource/MetaItem;->getVideos()Ljava/util/List;

    move-result-object p0

    if-eqz p0, :cond_3e

    check-cast p0, Ljava/lang/Iterable;

    invoke-interface {p0}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object p0

    :cond_22
    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result v4

    if-eqz v4, :cond_3a

    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v4

    move-object v5, v4

    check-cast v5, Lcom/stremio/core/types/resource/Video;

    invoke-virtual {v5}, Lcom/stremio/core/types/resource/Video;->getId()Ljava/lang/String;

    move-result-object v5

    invoke-static {v5, p1}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v5

    if-eqz v5, :cond_22

    goto :goto_3b

    :cond_3a
    move-object v4, v3

    :goto_3b
    check-cast v4, Lcom/stremio/core/types/resource/Video;

    goto :goto_3f

    :cond_3e
    move-object v4, v3

    :goto_3f
    if-nez p1, :cond_43

    .line 315
    const-string p1, ""

    :cond_43
    const-string p0, "yt_id:"

    const/4 v5, 0x2

    invoke-static {p1, p0, v1, v5, v3}, Lkotlin/text/StringsKt;->startsWith$default(Ljava/lang/String;Ljava/lang/String;ZILjava/lang/Object;)Z

    move-result p0

    if-eqz v4, :cond_56

    .line 316
    invoke-virtual {v4}, Lcom/stremio/core/types/resource/Video;->getStreams()Ljava/util/List;

    move-result-object p1

    if-eqz p1, :cond_56

    invoke-interface {p1}, Ljava/util/List;->isEmpty()Z

    move-result v2

    :cond_56
    if-nez p0, :cond_5b

    if-eqz v2, :cond_5b

    return v0

    :cond_5b
    return v1
.end method

.method private final clearStreams()V
    .registers 31

    move-object/from16 v0, p0

    .line 244
    iget-object v1, v0, Lcom/stremio/common/viewmodels/MetaDetailsViewModel;->_state:Lkotlinx/coroutines/flow/MutableStateFlow;

    iget-object v2, v0, Lcom/stremio/common/viewmodels/MetaDetailsViewModel;->state:Lkotlinx/coroutines/flow/StateFlow;

    invoke-interface {v2}, Lkotlinx/coroutines/flow/StateFlow;->getValue()Ljava/lang/Object;

    move-result-object v2

    move-object v3, v2

    check-cast v3, Lcom/stremio/common/viewmodels/state/MetaDetailsState;

    .line 245
    invoke-static {}, Lkotlin/collections/CollectionsKt;->emptyList()Ljava/util/List;

    move-result-object v13

    .line 246
    invoke-static {}, Lkotlin/collections/CollectionsKt;->emptyList()Ljava/util/List;

    move-result-object v12

    const v28, 0xfdf8ff

    const/16 v29, 0x0

    const/4 v4, 0x0

    const/4 v5, 0x0

    const/4 v6, 0x0

    const/4 v7, 0x0

    const/4 v8, 0x0

    const/4 v9, 0x0

    const/4 v10, 0x0

    const/4 v11, 0x0

    const/4 v14, 0x0

    const/4 v15, 0x0

    const/16 v16, 0x0

    const/16 v17, 0x0

    const/16 v18, 0x0

    const/16 v19, 0x0

    const/16 v20, 0x0

    const/16 v21, -0x1

    const/16 v22, 0x0

    const/16 v23, 0x0

    const/16 v24, 0x0

    const/16 v25, 0x0

    const/16 v26, 0x0

    const/16 v27, 0x0

    .line 244
    invoke-static/range {v3 .. v29}, Lcom/stremio/common/viewmodels/state/MetaDetailsState;->copy$default(Lcom/stremio/common/viewmodels/state/MetaDetailsState;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLcom/stremio/core/types/resource/MetaItem;Lcom/stremio/core/types/resource/Video;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/lang/String;ZLjava/lang/String;Lcom/stremio/core/types/resource/Stream;Ljava/lang/String;IIILcom/stremio/core/types/resource/Stream;ZZZZLstremio/core/types/RatingInfo;ILjava/lang/Object;)Lcom/stremio/common/viewmodels/state/MetaDetailsState;

    move-result-object v2

    invoke-interface {v1, v2}, Lkotlinx/coroutines/flow/MutableStateFlow;->setValue(Ljava/lang/Object;)V

    .line 250
    iget-object v1, v0, Lcom/stremio/common/viewmodels/MetaDetailsViewModel;->state:Lkotlinx/coroutines/flow/StateFlow;

    invoke-interface {v1}, Lkotlinx/coroutines/flow/StateFlow;->getValue()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/stremio/common/viewmodels/state/MetaDetailsState;

    invoke-virtual {v1}, Lcom/stremio/common/viewmodels/state/MetaDetailsState;->getType()Ljava/lang/String;

    move-result-object v1

    iget-object v2, v0, Lcom/stremio/common/viewmodels/MetaDetailsViewModel;->state:Lkotlinx/coroutines/flow/StateFlow;

    invoke-interface {v2}, Lkotlinx/coroutines/flow/StateFlow;->getValue()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/stremio/common/viewmodels/state/MetaDetailsState;

    invoke-virtual {v2}, Lcom/stremio/common/viewmodels/state/MetaDetailsState;->getId()Ljava/lang/String;

    move-result-object v2

    const/4 v3, 0x0

    const/4 v4, 0x0

    invoke-direct {v0, v1, v2, v3, v4}, Lcom/stremio/common/viewmodels/MetaDetailsViewModel;->loadMetaDetails(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)V

    return-void
.end method

.method private final defaultFocusedVideo()Lcom/stremio/core/types/resource/Video;
    .registers 8

    .line 289
    iget-object v0, p0, Lcom/stremio/common/viewmodels/MetaDetailsViewModel;->state:Lkotlinx/coroutines/flow/StateFlow;

    invoke-interface {v0}, Lkotlinx/coroutines/flow/StateFlow;->getValue()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/stremio/common/viewmodels/state/MetaDetailsState;

    invoke-virtual {v0}, Lcom/stremio/common/viewmodels/state/MetaDetailsState;->getVideos()Ljava/util/List;

    move-result-object v0

    .line 290
    move-object v1, v0

    check-cast v1, Ljava/lang/Iterable;

    invoke-interface {v1}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object v2

    :cond_13
    invoke-interface {v2}, Ljava/util/Iterator;->hasNext()Z

    move-result v3

    const/4 v4, 0x0

    if-eqz v3, :cond_38

    invoke-interface {v2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v3

    move-object v5, v3

    check-cast v5, Lcom/stremio/core/types/resource/Video;

    invoke-virtual {v5}, Lcom/stremio/core/types/resource/Video;->getId()Ljava/lang/String;

    move-result-object v5

    iget-object v6, p0, Lcom/stremio/common/viewmodels/MetaDetailsViewModel;->state:Lkotlinx/coroutines/flow/StateFlow;

    invoke-interface {v6}, Lkotlinx/coroutines/flow/StateFlow;->getValue()Ljava/lang/Object;

    move-result-object v6

    check-cast v6, Lcom/stremio/common/viewmodels/state/MetaDetailsState;

    invoke-virtual {v6}, Lcom/stremio/common/viewmodels/state/MetaDetailsState;->getVideoId()Ljava/lang/String;

    move-result-object v6

    invoke-static {v5, v6}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v5

    if-eqz v5, :cond_13

    goto :goto_39

    :cond_38
    move-object v3, v4

    :goto_39
    check-cast v3, Lcom/stremio/core/types/resource/Video;

    if-nez v3, :cond_61

    .line 291
    invoke-interface {v1}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object p0

    :cond_41
    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_55

    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    move-object v2, v1

    check-cast v2, Lcom/stremio/core/types/resource/Video;

    invoke-virtual {v2}, Lcom/stremio/core/types/resource/Video;->getCurrentVideo()Z

    move-result v2

    if-eqz v2, :cond_41

    move-object v4, v1

    :cond_55
    check-cast v4, Lcom/stremio/core/types/resource/Video;

    if-nez v4, :cond_60

    .line 292
    invoke-static {v0}, Lkotlin/collections/CollectionsKt;->firstOrNull(Ljava/util/List;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lcom/stremio/core/types/resource/Video;

    return-object p0

    :cond_60
    return-object v4

    :cond_61
    return-object v3
.end method

.method private final getSuggestedStream(Lcom/stremio/core/models/MetaDetails;)Lcom/stremio/core/types/resource/Stream;
    .registers 32

    move-object/from16 v0, p0

    .line 321
    invoke-virtual/range {p1 .. p1}, Lcom/stremio/core/models/MetaDetails;->getLastUsedStream()Lcom/stremio/core/models/LoadableStream;

    move-result-object v1

    const/4 v2, 0x0

    if-eqz v1, :cond_11

    invoke-static {v1}, Lcom/stremio/common/extensions/LoadableExtKt;->isLoading(Lcom/stremio/core/models/LoadableStream;)Z

    move-result v1

    const/4 v3, 0x1

    if-ne v1, v3, :cond_11

    move v2, v3

    .line 322
    :cond_11
    invoke-virtual/range {p1 .. p1}, Lcom/stremio/core/models/MetaDetails;->getLastUsedStream()Lcom/stremio/core/models/LoadableStream;

    move-result-object v1

    invoke-static {v1}, Lcom/stremio/common/extensions/LoadableExtKt;->getAsReady(Lcom/stremio/core/models/LoadableStream;)Lcom/stremio/core/types/resource/Stream;

    move-result-object v1

    if-nez v2, :cond_58

    if-nez v1, :cond_58

    .line 326
    iget-object v2, v0, Lcom/stremio/common/viewmodels/MetaDetailsViewModel;->_state:Lkotlinx/coroutines/flow/MutableStateFlow;

    iget-object v0, v0, Lcom/stremio/common/viewmodels/MetaDetailsViewModel;->state:Lkotlinx/coroutines/flow/StateFlow;

    invoke-interface {v0}, Lkotlinx/coroutines/flow/StateFlow;->getValue()Ljava/lang/Object;

    move-result-object v0

    move-object v3, v0

    check-cast v3, Lcom/stremio/common/viewmodels/state/MetaDetailsState;

    const v28, 0xfffff7

    const/16 v29, 0x0

    const/4 v4, 0x0

    const/4 v5, 0x0

    const/4 v6, 0x0

    const/4 v7, 0x0

    const/4 v8, 0x0

    const/4 v9, 0x0

    const/4 v10, 0x0

    const/4 v11, 0x0

    const/4 v12, 0x0

    const/4 v13, 0x0

    const/4 v14, 0x0

    const/4 v15, 0x0

    const/16 v16, 0x0

    const/16 v17, 0x0

    const/16 v18, 0x0

    const/16 v19, 0x0

    const/16 v20, 0x0

    const/16 v21, 0x0

    const/16 v22, 0x0

    const/16 v23, 0x0

    const/16 v24, 0x0

    const/16 v25, 0x0

    const/16 v26, 0x0

    const/16 v27, 0x0

    invoke-static/range {v3 .. v29}, Lcom/stremio/common/viewmodels/state/MetaDetailsState;->copy$default(Lcom/stremio/common/viewmodels/state/MetaDetailsState;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLcom/stremio/core/types/resource/MetaItem;Lcom/stremio/core/types/resource/Video;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/lang/String;ZLjava/lang/String;Lcom/stremio/core/types/resource/Stream;Ljava/lang/String;IIILcom/stremio/core/types/resource/Stream;ZZZZLstremio/core/types/RatingInfo;ILjava/lang/Object;)Lcom/stremio/common/viewmodels/state/MetaDetailsState;

    move-result-object v0

    invoke-interface {v2, v0}, Lkotlinx/coroutines/flow/MutableStateFlow;->setValue(Ljava/lang/Object;)V

    :cond_58
    return-object v1
.end method

.method private final loadMetaDetails(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)V
    .registers 40

    move-object/from16 v2, p0

    .line 126
    iget-object v0, v2, Lcom/stremio/common/viewmodels/MetaDetailsViewModel;->_autoPlayStream:Lkotlinx/coroutines/flow/MutableStateFlow;

    const/4 v7, 0x0

    invoke-interface {v0, v7}, Lkotlinx/coroutines/flow/MutableStateFlow;->setValue(Ljava/lang/Object;)V

    .line 127
    iget-object v0, v2, Lcom/stremio/common/viewmodels/MetaDetailsViewModel;->_state:Lkotlinx/coroutines/flow/MutableStateFlow;

    iget-object v1, v2, Lcom/stremio/common/viewmodels/MetaDetailsViewModel;->state:Lkotlinx/coroutines/flow/StateFlow;

    invoke-interface {v1}, Lkotlinx/coroutines/flow/StateFlow;->getValue()Ljava/lang/Object;

    move-result-object v1

    move-object v8, v1

    check-cast v8, Lcom/stremio/common/viewmodels/state/MetaDetailsState;

    move-object/from16 v11, p3

    .line 132
    invoke-direct {v2, v11}, Lcom/stremio/common/viewmodels/MetaDetailsViewModel;->canShowStreamList(Ljava/lang/String;)Z

    move-result v20

    const v33, 0xfff7f0

    const/16 v34, 0x0

    const/4 v13, 0x0

    const/4 v14, 0x0

    const/4 v15, 0x0

    const/16 v16, 0x0

    const/16 v17, 0x0

    const/16 v18, 0x0

    const/16 v19, 0x0

    const/16 v21, 0x0

    const/16 v22, 0x0

    const/16 v23, 0x0

    const/16 v24, 0x0

    const/16 v25, 0x0

    const/16 v26, 0x0

    const/16 v27, 0x0

    const/16 v28, 0x0

    const/16 v29, 0x0

    const/16 v30, 0x0

    const/16 v31, 0x0

    const/16 v32, 0x0

    move-object/from16 v9, p1

    move-object/from16 v10, p2

    move/from16 v12, p4

    .line 127
    invoke-static/range {v8 .. v34}, Lcom/stremio/common/viewmodels/state/MetaDetailsState;->copy$default(Lcom/stremio/common/viewmodels/state/MetaDetailsState;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLcom/stremio/core/types/resource/MetaItem;Lcom/stremio/core/types/resource/Video;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/lang/String;ZLjava/lang/String;Lcom/stremio/core/types/resource/Stream;Ljava/lang/String;IIILcom/stremio/core/types/resource/Stream;ZZZZLstremio/core/types/RatingInfo;ILjava/lang/Object;)Lcom/stremio/common/viewmodels/state/MetaDetailsState;

    move-result-object v1

    invoke-interface {v0, v1}, Lkotlinx/coroutines/flow/MutableStateFlow;->setValue(Ljava/lang/Object;)V

    .line 134
    iget-object v0, v2, Lcom/stremio/common/viewmodels/MetaDetailsViewModel;->autoPlayTimeoutJob:Lkotlinx/coroutines/Job;

    const/4 v1, 0x1

    if-eqz v0, :cond_56

    invoke-static {v0, v7, v1, v7}, Lkotlinx/coroutines/Job;->cancel$default(Lkotlinx/coroutines/Job;Ljava/util/concurrent/CancellationException;ILjava/lang/Object;)V

    :cond_56
    if-eqz p4, :cond_70

    .line 136
    move-object v0, v2

    check-cast v0, Landroidx/lifecycle/ViewModel;

    invoke-static {v0}, Landroidx/lifecycle/ViewModelKt;->getViewModelScope(Landroidx/lifecycle/ViewModel;)Lkotlinx/coroutines/CoroutineScope;

    move-result-object v8

    new-instance v0, Lcom/stremio/common/viewmodels/MetaDetailsViewModel$loadMetaDetails$1;

    invoke-direct {v0, v2, v7}, Lcom/stremio/common/viewmodels/MetaDetailsViewModel$loadMetaDetails$1;-><init>(Lcom/stremio/common/viewmodels/MetaDetailsViewModel;Lkotlin/coroutines/Continuation;)V

    move-object v11, v0

    check-cast v11, Lkotlin/jvm/functions/Function2;

    const/4 v12, 0x3

    const/4 v13, 0x0

    const/4 v9, 0x0

    const/4 v10, 0x0

    invoke-static/range {v8 .. v13}, Lkotlinx/coroutines/BuildersKt;->launch$default(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/CoroutineStart;Lkotlin/jvm/functions/Function2;ILjava/lang/Object;)Lkotlinx/coroutines/Job;

    move-result-object v0

    goto :goto_71

    :cond_70
    move-object v0, v7

    .line 135
    :goto_71
    iput-object v0, v2, Lcom/stremio/common/viewmodels/MetaDetailsViewModel;->autoPlayTimeoutJob:Lkotlinx/coroutines/Job;

    .line 143
    iget-object v0, v2, Lcom/stremio/common/viewmodels/MetaDetailsViewModel;->loadMetaJob:Lkotlinx/coroutines/Job;

    if-eqz v0, :cond_7a

    invoke-static {v0, v7, v1, v7}, Lkotlinx/coroutines/Job;->cancel$default(Lkotlinx/coroutines/Job;Ljava/util/concurrent/CancellationException;ILjava/lang/Object;)V

    .line 144
    :cond_7a
    iget-object v9, v2, Lcom/stremio/common/viewmodels/MetaDetailsViewModel;->stremioApi:Lcom/stremio/common/api/StremioApi;

    const/16 v14, 0x8

    const/4 v15, 0x0

    const/4 v13, 0x0

    move-object/from16 v10, p1

    move-object/from16 v11, p2

    move-object/from16 v12, p3

    invoke-static/range {v9 .. v15}, Lcom/stremio/common/api/StremioApi;->metaDetails$default(Lcom/stremio/common/api/StremioApi;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZILjava/lang/Object;)Lkotlinx/coroutines/flow/Flow;

    move-result-object v1

    .line 409
    new-instance v0, Lcom/stremio/common/viewmodels/MetaDetailsViewModel$loadMetaDetails$$inlined$filter$1;

    move-object/from16 v3, p1

    move-object/from16 v4, p2

    move-object/from16 v5, p3

    move/from16 v6, p4

    invoke-direct/range {v0 .. v6}, Lcom/stremio/common/viewmodels/MetaDetailsViewModel$loadMetaDetails$$inlined$filter$1;-><init>(Lkotlinx/coroutines/flow/Flow;Lcom/stremio/common/viewmodels/MetaDetailsViewModel;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)V

    check-cast v0, Lkotlinx/coroutines/flow/Flow;

    .line 157
    new-instance v1, Lcom/stremio/common/viewmodels/MetaDetailsViewModel$loadMetaDetails$3;

    invoke-direct {v1, v2, v7}, Lcom/stremio/common/viewmodels/MetaDetailsViewModel$loadMetaDetails$3;-><init>(Lcom/stremio/common/viewmodels/MetaDetailsViewModel;Lkotlin/coroutines/Continuation;)V

    check-cast v1, Lkotlin/jvm/functions/Function2;

    invoke-static {v0, v1}, Lkotlinx/coroutines/flow/FlowKt;->onEach(Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/flow/Flow;

    move-result-object v0

    .line 158
    move-object v1, v2

    check-cast v1, Landroidx/lifecycle/ViewModel;

    invoke-static {v1}, Landroidx/lifecycle/ViewModelKt;->getViewModelScope(Landroidx/lifecycle/ViewModel;)Lkotlinx/coroutines/CoroutineScope;

    move-result-object v1

    invoke-static {v0, v1}, Lkotlinx/coroutines/flow/FlowKt;->launchIn(Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/CoroutineScope;)Lkotlinx/coroutines/Job;

    move-result-object v0

    .line 144
    iput-object v0, v2, Lcom/stremio/common/viewmodels/MetaDetailsViewModel;->loadMetaJob:Lkotlinx/coroutines/Job;

    return-void
.end method

.method private final longestDescription(Lcom/stremio/core/types/resource/MetaItem;)Ljava/lang/String;
    .registers 6

    const/4 p0, 0x0

    if-eqz p1, :cond_8

    .line 380
    invoke-virtual {p1}, Lcom/stremio/core/types/resource/MetaItem;->getDescription()Ljava/lang/String;

    move-result-object v0

    goto :goto_9

    :cond_8
    move-object v0, p0

    :goto_9
    if-nez v0, :cond_d

    const-string v0, ""

    :cond_d
    if-eqz p1, :cond_7a

    .line 381
    invoke-virtual {p1}, Lcom/stremio/core/types/resource/MetaItem;->getVideos()Ljava/util/List;

    move-result-object p1

    if-eqz p1, :cond_7a

    check-cast p1, Ljava/lang/Iterable;

    .line 469
    new-instance v1, Ljava/util/ArrayList;

    const/16 v2, 0xa

    invoke-static {p1, v2}, Lkotlin/collections/CollectionsKt;->collectionSizeOrDefault(Ljava/lang/Iterable;I)I

    move-result v2

    invoke-direct {v1, v2}, Ljava/util/ArrayList;-><init>(I)V

    check-cast v1, Ljava/util/Collection;

    .line 470
    invoke-interface {p1}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object p1

    :goto_28
    invoke-interface {p1}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_3f

    invoke-interface {p1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    .line 471
    check-cast v2, Lcom/stremio/core/types/resource/Video;

    .line 382
    invoke-virtual {v2}, Lcom/stremio/core/types/resource/Video;->getOverview()Ljava/lang/String;

    move-result-object v2

    if-nez v2, :cond_3b

    move-object v2, v0

    .line 471
    :cond_3b
    invoke-interface {v1, v2}, Ljava/util/Collection;->add(Ljava/lang/Object;)Z

    goto :goto_28

    .line 472
    :cond_3f
    check-cast v1, Ljava/util/List;

    .line 381
    check-cast v1, Ljava/lang/Iterable;

    .line 473
    invoke-interface {v1}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object p1

    .line 474
    invoke-interface {p1}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-nez v1, :cond_4e

    goto :goto_75

    .line 475
    :cond_4e
    invoke-interface {p1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object p0

    .line 476
    invoke-interface {p1}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-nez v1, :cond_59

    goto :goto_75

    .line 477
    :cond_59
    move-object v1, p0

    check-cast v1, Ljava/lang/String;

    .line 383
    invoke-virtual {v1}, Ljava/lang/String;->length()I

    move-result v1

    .line 479
    :cond_60
    invoke-interface {p1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    .line 480
    move-object v3, v2

    check-cast v3, Ljava/lang/String;

    .line 383
    invoke-virtual {v3}, Ljava/lang/String;->length()I

    move-result v3

    if-ge v1, v3, :cond_6f

    move-object p0, v2

    move v1, v3

    .line 485
    :cond_6f
    invoke-interface {p1}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-nez v2, :cond_60

    .line 383
    :goto_75
    check-cast p0, Ljava/lang/String;

    if-eqz p0, :cond_7a

    return-object p0

    :cond_7a
    return-object v0
.end method

.method private final rate(Lstremio/core/types/Rating;)V
    .registers 2

    .line 285
    iget-object p0, p0, Lcom/stremio/common/viewmodels/MetaDetailsViewModel;->stremioApi:Lcom/stremio/common/api/StremioApi;

    invoke-virtual {p0, p1}, Lcom/stremio/common/api/StremioApi;->rate(Lstremio/core/types/Rating;)V

    return-void
.end method

.method private final ratingInfo(Lcom/stremio/core/models/MetaDetails;)Lstremio/core/types/RatingInfo;
    .registers 2

    .line 387
    invoke-virtual {p1}, Lcom/stremio/core/models/MetaDetails;->getRatingInfo()Lcom/stremio/core/models/LoadableRatingInfo;

    move-result-object p1

    if-eqz p1, :cond_e

    invoke-static {p1}, Lcom/stremio/common/extensions/LoadableExtKt;->getAsReady(Lcom/stremio/core/models/LoadableRatingInfo;)Lstremio/core/types/RatingInfo;

    move-result-object p1

    if-nez p1, :cond_d

    goto :goto_e

    :cond_d
    return-object p1

    :cond_e
    :goto_e
    iget-object p0, p0, Lcom/stremio/common/viewmodels/MetaDetailsViewModel;->state:Lkotlinx/coroutines/flow/StateFlow;

    invoke-interface {p0}, Lkotlinx/coroutines/flow/StateFlow;->getValue()Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lcom/stremio/common/viewmodels/state/MetaDetailsState;

    invoke-virtual {p0}, Lcom/stremio/common/viewmodels/state/MetaDetailsState;->getRatingInfo()Lstremio/core/types/RatingInfo;

    move-result-object p0

    return-object p0
.end method

.method private final selectAddon(Lcom/stremio/common/viewmodels/state/Addon;)V
    .registers 32

    move-object/from16 v0, p0

    .line 234
    iget-object v1, v0, Lcom/stremio/common/viewmodels/MetaDetailsViewModel;->state:Lkotlinx/coroutines/flow/StateFlow;

    invoke-interface {v1}, Lkotlinx/coroutines/flow/StateFlow;->getValue()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/stremio/common/viewmodels/state/MetaDetailsState;

    invoke-virtual {v1}, Lcom/stremio/common/viewmodels/state/MetaDetailsState;->getAddonStreams()Ljava/util/List;

    move-result-object v1

    check-cast v1, Ljava/lang/Iterable;

    invoke-interface {v1}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :cond_14
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_2e

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    move-object v3, v2

    check-cast v3, Lcom/stremio/common/viewmodels/MetaDetailsViewModel$AddonStreams;

    invoke-virtual {v3}, Lcom/stremio/common/viewmodels/MetaDetailsViewModel$AddonStreams;->getAddon()Lcom/stremio/common/viewmodels/state/Addon;

    move-result-object v3

    move-object/from16 v4, p1

    invoke-static {v3, v4}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v3

    if-eqz v3, :cond_14

    goto :goto_2f

    :cond_2e
    const/4 v2, 0x0

    :goto_2f
    check-cast v2, Lcom/stremio/common/viewmodels/MetaDetailsViewModel$AddonStreams;

    .line 235
    iget-object v1, v0, Lcom/stremio/common/viewmodels/MetaDetailsViewModel;->state:Lkotlinx/coroutines/flow/StateFlow;

    invoke-interface {v1}, Lkotlinx/coroutines/flow/StateFlow;->getValue()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/stremio/common/viewmodels/state/MetaDetailsState;

    invoke-virtual {v1}, Lcom/stremio/common/viewmodels/state/MetaDetailsState;->getAddonStreams()Ljava/util/List;

    move-result-object v1

    invoke-static {v1, v2}, Lkotlin/collections/CollectionsKt;->indexOf(Ljava/util/List;Ljava/lang/Object;)I

    move-result v21

    if-eqz v2, :cond_49

    .line 236
    invoke-virtual {v2}, Lcom/stremio/common/viewmodels/MetaDetailsViewModel$AddonStreams;->getStreams()Ljava/util/List;

    move-result-object v1

    if-nez v1, :cond_4d

    :cond_49
    invoke-static {}, Lkotlin/collections/CollectionsKt;->emptyList()Ljava/util/List;

    move-result-object v1

    :cond_4d
    move-object v13, v1

    .line 237
    iget-object v1, v0, Lcom/stremio/common/viewmodels/MetaDetailsViewModel;->_state:Lkotlinx/coroutines/flow/MutableStateFlow;

    iget-object v0, v0, Lcom/stremio/common/viewmodels/MetaDetailsViewModel;->state:Lkotlinx/coroutines/flow/StateFlow;

    invoke-interface {v0}, Lkotlinx/coroutines/flow/StateFlow;->getValue()Ljava/lang/Object;

    move-result-object v0

    move-object v3, v0

    check-cast v3, Lcom/stremio/common/viewmodels/state/MetaDetailsState;

    const v28, 0xfdfdff

    const/16 v29, 0x0

    const/4 v4, 0x0

    const/4 v5, 0x0

    const/4 v6, 0x0

    const/4 v7, 0x0

    const/4 v8, 0x0

    const/4 v9, 0x0

    const/4 v10, 0x0

    const/4 v11, 0x0

    const/4 v12, 0x0

    const/4 v14, 0x0

    const/4 v15, 0x0

    const/16 v16, 0x0

    const/16 v17, 0x0

    const/16 v18, 0x0

    const/16 v19, 0x0

    const/16 v20, 0x0

    const/16 v22, 0x0

    const/16 v23, 0x0

    const/16 v24, 0x0

    const/16 v25, 0x0

    const/16 v26, 0x0

    const/16 v27, 0x0

    invoke-static/range {v3 .. v29}, Lcom/stremio/common/viewmodels/state/MetaDetailsState;->copy$default(Lcom/stremio/common/viewmodels/state/MetaDetailsState;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLcom/stremio/core/types/resource/MetaItem;Lcom/stremio/core/types/resource/Video;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/lang/String;ZLjava/lang/String;Lcom/stremio/core/types/resource/Stream;Ljava/lang/String;IIILcom/stremio/core/types/resource/Stream;ZZZZLstremio/core/types/RatingInfo;ILjava/lang/Object;)Lcom/stremio/common/viewmodels/state/MetaDetailsState;

    move-result-object v0

    invoke-interface {v1, v0}, Lkotlinx/coroutines/flow/MutableStateFlow;->setValue(Ljava/lang/Object;)V

    return-void
.end method

.method private final selectSeason(J)V
    .registers 34

    move-object/from16 v0, p0

    .line 218
    iget-object v1, v0, Lcom/stremio/common/viewmodels/MetaDetailsViewModel;->state:Lkotlinx/coroutines/flow/StateFlow;

    invoke-interface {v1}, Lkotlinx/coroutines/flow/StateFlow;->getValue()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/stremio/common/viewmodels/state/MetaDetailsState;

    invoke-virtual {v1}, Lcom/stremio/common/viewmodels/state/MetaDetailsState;->getSelectedVideoIndex()I

    move-result v1

    .line 219
    iget-object v2, v0, Lcom/stremio/common/viewmodels/MetaDetailsViewModel;->state:Lkotlinx/coroutines/flow/StateFlow;

    invoke-interface {v2}, Lkotlinx/coroutines/flow/StateFlow;->getValue()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/stremio/common/viewmodels/state/MetaDetailsState;

    invoke-virtual {v2}, Lcom/stremio/common/viewmodels/state/MetaDetailsState;->getSeasons()Ljava/util/List;

    move-result-object v2

    invoke-static/range {p1 .. p2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v3

    invoke-interface {v2, v3}, Ljava/util/List;->indexOf(Ljava/lang/Object;)I

    move-result v20

    const/4 v2, -0x1

    if-eq v1, v2, :cond_e5

    .line 221
    iget-object v1, v0, Lcom/stremio/common/viewmodels/MetaDetailsViewModel;->state:Lkotlinx/coroutines/flow/StateFlow;

    invoke-interface {v1}, Lkotlinx/coroutines/flow/StateFlow;->getValue()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/stremio/common/viewmodels/state/MetaDetailsState;

    invoke-virtual {v1}, Lcom/stremio/common/viewmodels/state/MetaDetailsState;->getVideo()Lcom/stremio/core/types/resource/Video;

    move-result-object v1

    if-eqz v1, :cond_42

    invoke-virtual {v1}, Lcom/stremio/core/types/resource/Video;->getSeriesInfo()Lcom/stremio/core/types/resource/Video$SeriesInfo;

    move-result-object v1

    if-eqz v1, :cond_42

    invoke-virtual {v1}, Lcom/stremio/core/types/resource/Video$SeriesInfo;->getSeason()J

    move-result-wide v3

    invoke-static {v3, v4}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v1

    goto :goto_43

    :cond_42
    const/4 v1, 0x0

    :goto_43
    const/4 v3, 0x0

    const/4 v4, 0x1

    if-nez v1, :cond_48

    goto :goto_52

    .line 222
    :cond_48
    invoke-virtual {v1}, Ljava/lang/Long;->longValue()J

    move-result-wide v5

    cmp-long v1, v5, p1

    if-nez v1, :cond_52

    move v1, v4

    goto :goto_53

    :cond_52
    :goto_52
    move v1, v3

    :goto_53
    if-ne v1, v4, :cond_74

    .line 223
    iget-object v1, v0, Lcom/stremio/common/viewmodels/MetaDetailsViewModel;->state:Lkotlinx/coroutines/flow/StateFlow;

    invoke-interface {v1}, Lkotlinx/coroutines/flow/StateFlow;->getValue()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/stremio/common/viewmodels/state/MetaDetailsState;

    invoke-virtual {v1}, Lcom/stremio/common/viewmodels/state/MetaDetailsState;->getVideos()Ljava/util/List;

    move-result-object v1

    iget-object v2, v0, Lcom/stremio/common/viewmodels/MetaDetailsViewModel;->state:Lkotlinx/coroutines/flow/StateFlow;

    invoke-interface {v2}, Lkotlinx/coroutines/flow/StateFlow;->getValue()Ljava/lang/Object;

    move-result-object v2



... [truncated: exceeds 60000 bytes, total 185596 bytes]