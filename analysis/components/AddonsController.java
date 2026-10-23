// [jadx java-decompile failed → smali]
// target: com.stremio.tv.views.addons.AddonsController
// markers: Code decompiled incorrectly, please refer to instructions dump
// (Java body was an anti-decompile stub; showing smali. Pass smali_fallback=false for the partial Java.)

###### Class com.stremio.tv.views.addons.AddonsController (com.stremio.tv.views.addons.AddonsController)
.class public final Lcom/stremio/tv/views/addons/AddonsController;
.super Ljp/co/cyberagent/lounge/LoungeController;
.source "AddonsController.kt"


# annotations
.annotation system Ldalvik/annotation/SourceDebugExtension;
    value = "SMAP\nAddonsController.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AddonsController.kt\ncom/stremio/tv/views/addons/AddonsController\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,23:1\n2199#2,2:24\n*S KotlinDebug\n*F\n+ 1 AddonsController.kt\ncom/stremio/tv/views/addons/AddonsController\n*L\n19#1:24,2\n*E\n"
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0008\u0007\n\u0002\u0010\u000e\n\u0002\u0008\u0007\n\u0002\u0010\u0002\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0000\u0008\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\u0008\u0004\u0010\u0005J\u000e\u0010\u0017\u001a\u00020\u0018H\u0094@\u00a2\u0006\u0002\u0010\u0019R7\u0010\t\u001a\u0008\u0012\u0004\u0012\u00020\u00080\u00072\u000c\u0010\u0006\u001a\u0008\u0012\u0004\u0012\u00020\u00080\u00078F@FX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\u0008\u000e\u0010\u000f\u001a\u0004\u0008\n\u0010\u000b\"\u0004\u0008\u000c\u0010\rR/\u0010\u0011\u001a\u0004\u0018\u00010\u00102\u0008\u0010\u0006\u001a\u0004\u0018\u00010\u00108F@FX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\u0008\u0016\u0010\u000f\u001a\u0004\u0008\u0012\u0010\u0013\"\u0004\u0008\u0014\u0010\u0015\u00ca\u0001\u000c\u0008\u001b\u0012\u0008\u0008\u001c\u0012\u0004\u0008\u0003\u0010\u0000\u00a8\u0006\u001a"
    }
    d2 = {
        "Lcom/stremio/tv/views/addons/AddonsController;",
        "Ljp/co/cyberagent/lounge/LoungeController;",
        "lifecycle",
        "Landroidx/lifecycle/Lifecycle;",
        "<init>",
        "(Landroidx/lifecycle/Lifecycle;)V",
        "<set-?>",
        "",
        "Lcom/stremio/core/types/addon/AddonDescriptor;",
        "rows",
        "getRows",
        "()Ljava/util/List;",
        "setRows",
        "(Ljava/util/List;)V",
        "rows$delegate",
        "Lkotlin/properties/ReadWriteProperty;",
        "",
        "message",
        "getMessage",
        "()Ljava/lang/String;",
        "setMessage",
        "(Ljava/lang/String;)V",
        "message$delegate",
        "buildModels",
        "",
        "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;",
        "Stremio:androidTV-com.stremio.one-1.11.2-40000002_release",
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
.field static final synthetic $$delegatedProperties:[Lkotlin/reflect/KProperty;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "[",
            "Lkotlin/reflect/KProperty<",
            "Ljava/lang/Object;",
            ">;"
        }
    .end annotation
.end field

.field public static final $stable:I


# instance fields
.field private final message$delegate:Lkotlin/properties/ReadWriteProperty;

.field private final rows$delegate:Lkotlin/properties/ReadWriteProperty;


# direct methods
.method static constructor <clinit>()V
    .registers 6

    const/4 v0, 0x2

    new-array v0, v0, [Lkotlin/reflect/KProperty;

    new-instance v1, Lkotlin/jvm/internal/MutablePropertyReference1Impl;

    const-string/jumbo v2, "rows"

    const-string v3, "getRows()Ljava/util/List;"

    const-class v4, Lcom/stremio/tv/views/addons/AddonsController;

    const/4 v5, 0x0

    invoke-direct {v1, v4, v2, v3, v5}, Lkotlin/jvm/internal/MutablePropertyReference1Impl;-><init>(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;I)V

    aput-object v1, v0, v5

    new-instance v1, Lkotlin/jvm/internal/MutablePropertyReference1Impl;

    const-string v2, "message"

    const-string v3, "getMessage()Ljava/lang/String;"

    invoke-direct {v1, v4, v2, v3, v5}, Lkotlin/jvm/internal/MutablePropertyReference1Impl;-><init>(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;I)V

    const/4 v2, 0x1

    aput-object v1, v0, v2

    sput-object v0, Lcom/stremio/tv/views/addons/AddonsController;->$$delegatedProperties:[Lkotlin/reflect/KProperty;

    const/16 v0, 0x8

    sput v0, Lcom/stremio/tv/views/addons/AddonsController;->$stable:I

    return-void
.end method

.method public constructor <init>(Landroidx/lifecycle/Lifecycle;)V
    .registers 4

    const-string v0, "lifecycle"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    const/4 v0, 0x0

    const/4 v1, 0x2

    .line 10
    invoke-direct {p0, p1, v0, v1, v0}, Ljp/co/cyberagent/lounge/LoungeController;-><init>(Landroidx/lifecycle/Lifecycle;Lkotlinx/coroutines/CoroutineDispatcher;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    .line 12
    invoke-static {}, Lkotlin/collections/CollectionsKt;->emptyList()Ljava/util/List;

    move-result-object p1

    invoke-static {p1, v0, v1, v0}, Ljp/co/cyberagent/lounge/LoungeControllerPropertyKt;->loungeProp$default(Ljava/lang/Object;Ljp/co/cyberagent/lounge/LoungePropertyPredicate;ILjava/lang/Object;)Lkotlin/properties/ReadWriteProperty;

    move-result-object p1

    iput-object p1, p0, Lcom/stremio/tv/views/addons/AddonsController;->rows$delegate:Lkotlin/properties/ReadWriteProperty;

    .line 13
    invoke-static {v0, v0, v1, v0}, Ljp/co/cyberagent/lounge/LoungeControllerPropertyKt;->loungeProp$default(Ljava/lang/Object;Ljp/co/cyberagent/lounge/LoungePropertyPredicate;ILjava/lang/Object;)Lkotlin/properties/ReadWriteProperty;

    move-result-object p1

    iput-object p1, p0, Lcom/stremio/tv/views/addons/AddonsController;->message$delegate:Lkotlin/properties/ReadWriteProperty;

    return-void
.end method


# virtual methods
.method protected buildModels(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .registers 10
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lkotlin/coroutines/Continuation<",
            "-",
            "Lkotlin/Unit;",
            ">;)",
            "Ljava/lang/Object;"
        }
    .end annotation

    instance-of v0, p1, Lcom/stremio/tv/views/addons/AddonsController$buildModels$1;

    if-eqz v0, :cond_14

    move-object v0, p1

    check-cast v0, Lcom/stremio/tv/views/addons/AddonsController$buildModels$1;

    iget v1, v0, Lcom/stremio/tv/views/addons/AddonsController$buildModels$1;->label:I

    const/high16 v2, -0x80000000

    and-int/2addr v1, v2

    if-eqz v1, :cond_14

    iget p1, v0, Lcom/stremio/tv/views/addons/AddonsController$buildModels$1;->label:I

    sub-int/2addr p1, v2

    iput p1, v0, Lcom/stremio/tv/views/addons/AddonsController$buildModels$1;->label:I

    goto :goto_19

    :cond_14
    new-instance v0, Lcom/stremio/tv/views/addons/AddonsController$buildModels$1;

    invoke-direct {v0, p0, p1}, Lcom/stremio/tv/views/addons/AddonsController$buildModels$1;-><init>(Lcom/stremio/tv/views/addons/AddonsController;Lkotlin/coroutines/Continuation;)V

    :goto_19
    iget-object p1, v0, Lcom/stremio/tv/views/addons/AddonsController$buildModels$1;->result:Ljava/lang/Object;

    invoke-static {}, Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;

    move-result-object v1

    .line 15
    iget v2, v0, Lcom/stremio/tv/views/addons/AddonsController$buildModels$1;->label:I

    const/4 v3, 0x2

    const/4 v4, 0x1

    if-eqz v2, :cond_47

    if-eq v2, v4, :cond_43

    if-ne v2, v3, :cond_3b

    iget-object v2, v0, Lcom/stremio/tv/views/addons/AddonsController$buildModels$1;->L$3:Ljava/lang/Object;

    check-cast v2, Lcom/stremio/core/types/addon/AddonDescriptor;

    iget-object v2, v0, Lcom/stremio/tv/views/addons/AddonsController$buildModels$1;->L$2:Ljava/lang/Object;

    iget-object v2, v0, Lcom/stremio/tv/views/addons/AddonsController$buildModels$1;->L$1:Ljava/lang/Object;

    check-cast v2, Ljava/util/Iterator;

    iget-object v4, v0, Lcom/stremio/tv/views/addons/AddonsController$buildModels$1;->L$0:Ljava/lang/Object;

    check-cast v4, Ljava/lang/Iterable;

    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto :goto_75

    :cond_3b
    new-instance p0, Ljava/lang/IllegalStateException;

    const-string p1, "call to \'resume\' before \'invoke\' with coroutine"

    invoke-direct {p0, p1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw p0

    :cond_43
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    goto :goto_67

    :cond_47
    invoke-static {p1}, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V

    .line 16
    invoke-virtual {p0}, Lcom/stremio/tv/views/addons/AddonsController;->getMessage()Ljava/lang/String;

    move-result-object p1

    if-eqz p1, :cond_6a

    .line 17
    new-instance p1, Lcom/stremio/tv/views/metarow/model/CatalogErrorModel;

    invoke-virtual {p0}, Lcom/stremio/tv/views/addons/AddonsController;->getMessage()Ljava/lang/String;

    move-result-object v2

    invoke-static {v2}, Lkotlin/jvm/internal/Intrinsics;->checkNotNull(Ljava/lang/Object;)V

    invoke-direct {p1, v2}, Lcom/stremio/tv/views/metarow/model/CatalogErrorModel;-><init>(Ljava/lang/String;)V

    check-cast p1, Ljp/co/cyberagent/lounge/LoungeModel;

    iput v4, v0, Lcom/stremio/tv/views/addons/AddonsController$buildModels$1;->label:I

    invoke-virtual {p0, p1, v0}, Lcom/stremio/tv/views/addons/AddonsController;->unaryPlus(Ljp/co/cyberagent/lounge/LoungeModel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    if-ne p0, v1, :cond_67

    goto :goto_a5

    .line 21
    :cond_67
    :goto_67
    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0

    .line 19
    :cond_6a
    invoke-virtual {p0}, Lcom/stremio/tv/views/addons/AddonsController;->getRows()Ljava/util/List;

    move-result-object p1

    check-cast p1, Ljava/lang/Iterable;

    .line 24
    invoke-interface {p1}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object v2

    move-object v4, p1

    :cond_75
    :goto_75
    invoke-interface {v2}, Ljava/util/Iterator;->hasNext()Z

    move-result p1

    if-eqz p1, :cond_a6

    invoke-interface {v2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object p1

    move-object v5, p1

    check-cast v5, Lcom/stremio/core/types/addon/AddonDescriptor;

    .line 19
    new-instance v6, Lcom/stremio/tv/views/addons/model/DescriptorModel;

    invoke-direct {v6, v5}, Lcom/stremio/tv/views/addons/model/DescriptorModel;-><init>(Lcom/stremio/core/types/addon/AddonDescriptor;)V

    check-cast v6, Ljp/co/cyberagent/lounge/LoungeModel;

    invoke-static {v4}, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v7

    iput-object v7, v0, Lcom/stremio/tv/views/addons/AddonsController$buildModels$1;->L$0:Ljava/lang/Object;

    iput-object v2, v0, Lcom/stremio/tv/views/addons/AddonsController$buildModels$1;->L$1:Ljava/lang/Object;

    invoke-static {p1}, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p1

    iput-object p1, v0, Lcom/stremio/tv/views/addons/AddonsController$buildModels$1;->L$2:Ljava/lang/Object;

    invoke-static {v5}, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p1

    iput-object p1, v0, Lcom/stremio/tv/views/addons/AddonsController$buildModels$1;->L$3:Ljava/lang/Object;

    iput v3, v0, Lcom/stremio/tv/views/addons/AddonsController$buildModels$1;->label:I

    invoke-virtual {p0, v6, v0}, Lcom/stremio/tv/views/addons/AddonsController;->unaryPlus(Ljp/co/cyberagent/lounge/LoungeModel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p1

    if-ne p1, v1, :cond_75

    :goto_a5
    return-object v1

    .line 21
    :cond_a6
    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0
.end method

.method public final getMessage()Ljava/lang/String;
    .registers 4

    .line 13
    iget-object v0, p0, Lcom/stremio/tv/views/addons/AddonsController;->message$delegate:Lkotlin/properties/ReadWriteProperty;

    sget-object v1, Lcom/stremio/tv/views/addons/AddonsController;->$$delegatedProperties:[Lkotlin/reflect/KProperty;

    const/4 v2, 0x1

    aget-object v1, v1, v2

    invoke-interface {v0, p0, v1}, Lkotlin/properties/ReadWriteProperty;->getValue(Ljava/lang/Object;Lkotlin/reflect/KProperty;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Ljava/lang/String;

    return-object p0
.end method

.method public final getRows()Ljava/util/List;
    .registers 4
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/List<",
            "Lcom/stremio/core/types/addon/AddonDescriptor;",
            ">;"
        }
    .end annotation

    .line 12
    iget-object v0, p0, Lcom/stremio/tv/views/addons/AddonsController;->rows$delegate:Lkotlin/properties/ReadWriteProperty;

    sget-object v1, Lcom/stremio/tv/views/addons/AddonsController;->$$delegatedProperties:[Lkotlin/reflect/KProperty;

    const/4 v2, 0x0

    aget-object v1, v1, v2

    invoke-interface {v0, p0, v1}, Lkotlin/properties/ReadWriteProperty;->getValue(Ljava/lang/Object;Lkotlin/reflect/KProperty;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Ljava/util/List;

    return-object p0
.end method

.method public final setMessage(Ljava/lang/String;)V
    .registers 5

    .line 13
    iget-object v0, p0, Lcom/stremio/tv/views/addons/AddonsController;->message$delegate:Lkotlin/properties/ReadWriteProperty;

    sget-object v1, Lcom/stremio/tv/views/addons/AddonsController;->$$delegatedProperties:[Lkotlin/reflect/KProperty;

    const/4 v2, 0x1

    aget-object v1, v1, v2

    invoke-interface {v0, p0, v1, p1}, Lkotlin/properties/ReadWriteProperty;->setValue(Ljava/lang/Object;Lkotlin/reflect/KProperty;Ljava/lang/Object;)V

    return-void
.end method

.method public final setRows(Ljava/util/List;)V
    .registers 5
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "Lcom/stremio/core/types/addon/AddonDescriptor;",
            ">;)V"
        }
    .end annotation

    const-string v0, "<set-?>"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 12
    iget-object v0, p0, Lcom/stremio/tv/views/addons/AddonsController;->rows$delegate:Lkotlin/properties/ReadWriteProperty;

    sget-object v1, Lcom/stremio/tv/views/addons/AddonsController;->$$delegatedProperties:[Lkotlin/reflect/KProperty;

    const/4 v2, 0x0

    aget-object v1, v1, v2

    invoke-interface {v0, p0, v1, p1}, Lkotlin/properties/ReadWriteProperty;->setValue(Ljava/lang/Object;Lkotlin/reflect/KProperty;Ljava/lang/Object;)V

    return-void
.end method

###### Class com.stremio.tv.views.addons.AddonsController.AnonymousClass1 (com.stremio.tv.views.addons.AddonsController$buildModels$1)
.class final Lcom/stremio/tv/views/addons/AddonsController$buildModels$1;
.super Lkotlin/coroutines/jvm/internal/ContinuationImpl;
.source "AddonsController.kt"


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/stremio/tv/views/addons/AddonsController;->buildModels(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x18
    name = null
.end annotation

.annotation runtime Lkotlin/Metadata;
    k = 0x3
    mv = {
        0x2,
        0x4,
        0x0
    }
    xi = 0x230
.end annotation

.annotation runtime Lkotlin/coroutines/jvm/internal/DebugMetadata;
    c = "com.stremio.tv.views.addons.AddonsController"
    f = "AddonsController.kt"
    i = {
        0x1,
        0x1,
        0x1
    }
    l = {
        0x11,
        0x13
    }
    m = "buildModels"
    n = {
        "$this$forEach$iv",
        "element$iv",
        "it"
    }
    nl = {
        0x15,
        0x18
    }
    s = {
        "L$0",
        "L$2",
        "L$3"
    }
    v = 0x2
.end annotation


# instance fields
.field L$0:Ljava/lang/Object;

.field L$1:Ljava/lang/Object;

.field L$2:Ljava/lang/Object;

.field L$3:Ljava/lang/Object;

.field label:I

.field synthetic result:Ljava/lang/Object;

.field final synthetic this$0:Lcom/stremio/tv/views/addons/AddonsController;


# direct methods
.method constructor <init>(Lcom/stremio/tv/views/addons/AddonsController;Lkotlin/coroutines/Continuation;)V
    .registers 3
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lcom/stremio/tv/views/addons/AddonsController;",
            "Lkotlin/coroutines/Continuation<",
            "-",
            "Lcom/stremio/tv/views/addons/AddonsController$buildModels$1;",
            ">;)V"
        }
    .end annotation

    iput-object p1, p0, Lcom/stremio/tv/views/addons/AddonsController$buildModels$1;->this$0:Lcom/stremio/tv/views/addons/AddonsController;

    invoke-direct {p0, p2}, Lkotlin/coroutines/jvm/internal/ContinuationImpl;-><init>(Lkotlin/coroutines/Continuation;)V

    return-void
.end method


# virtual methods
.method public final invokeSuspend(Ljava/lang/Object;)Ljava/lang/Object;
    .registers 3

    iput-object p1, p0, Lcom/stremio/tv/views/addons/AddonsController$buildModels$1;->result:Ljava/lang/Object;

    iget p1, p0, Lcom/stremio/tv/views/addons/AddonsController$buildModels$1;->label:I

    const/high16 v0, -0x80000000

    or-int/2addr p1, v0

    iput p1, p0, Lcom/stremio/tv/views/addons/AddonsController$buildModels$1;->label:I

    iget-object p1, p0, Lcom/stremio/tv/views/addons/AddonsController$buildModels$1;->this$0:Lcom/stremio/tv/views/addons/AddonsController;

    check-cast p0, Lkotlin/coroutines/Continuation;

    invoke-virtual {p1, p0}, Lcom/stremio/tv/views/addons/AddonsController;->buildModels(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method
