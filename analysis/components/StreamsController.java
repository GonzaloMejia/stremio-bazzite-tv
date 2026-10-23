package com.stremio.tv.views.metadetails.streams;

import androidx.leanback.widget.SinglePresenterSelector;
import com.stremio.core.types.resource.Stream;
import com.stremio.tv.R;
import com.stremio.tv.widget.FixedCenterListRowPresenter;
import com.stremio.tv.widget.FixedStartListRowPresenter;
import com.stremio.tv.widget.SingleRowLoungeController;
import jp.co.cyberagent.lounge.LoungeModel;
import jp.co.cyberagent.lounge.databinding.SimpleDataBindingPresenter;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: StreamsController.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u0000 \u00122\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001\u0012B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u0002H\u0016J\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0002R\u001c\u0010\b\u001a\u0004\u0018\u00010\u0002X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fÊ\u0001\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\u0003\u0010\u0000¨\u0006\u0013"}, d2 = {"Lcom/stremio/tv/views/metadetails/streams/StreamsController;", "Lcom/stremio/tv/widget/SingleRowLoungeController;", "Lcom/stremio/core/types/resource/Stream;", "Lcom/stremio/tv/views/metadetails/streams/StreamModel;", "showHoverCard", "", "<init>", "(Z)V", "selectedItem", "getSelectedItem", "()Lcom/stremio/core/types/resource/Stream;", "setSelectedItem", "(Lcom/stremio/core/types/resource/Stream;)V", "buildModel", "item", "setSelectedStream", "", "stream", "Companion", "Stremio:androidTV-com.stremio.one-1.11.2-40000002_release", "Landroidx/compose/runtime/internal/StabilityInferred;", "parameters"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class StreamsController extends SingleRowLoungeController<Stream, StreamModel> {
    public static final int $stable = 8;
    private static final String KEY = "streams";
    private static final SimpleDataBindingPresenter<LoungeModel> hoverCardPresenter;
    private static final FixedCenterListRowPresenter presenter;
    private static final FixedStartListRowPresenter presenterWithHoverCard;
    private Stream selectedItem;

    public StreamsController(boolean z) {
        super(KEY, z ? presenterWithHoverCard : presenter);
    }

    public final Stream getSelectedItem() {
        return this.selectedItem;
    }

    public final void setSelectedItem(Stream stream) {
        this.selectedItem = stream;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.stremio.tv.widget.SingleRowLoungeController
    public StreamModel buildModel(Stream item) {
        Intrinsics.checkNotNullParameter(item, "item");
        StreamModel streamModel = new StreamModel(item, null, 2, 0 == true ? 1 : 0);
        streamModel.getSelected().set(Intrinsics.areEqual(item, this.selectedItem));
        return streamModel;
    }

    public final void setSelectedStream(Stream stream) {
        Intrinsics.checkNotNullParameter(stream, "stream");
        this.selectedItem = stream;
        for (StreamModel streamModel : getModels()) {
            streamModel.getSelected().set(Intrinsics.areEqual(streamModel.getStream(), stream));
        }
    }

    static {
        DefaultConstructorMarker defaultConstructorMarker = null;
        INSTANCE = new Companion(defaultConstructorMarker);
        presenter = new FixedCenterListRowPresenter(1, false, 2, defaultConstructorMarker);
        SimpleDataBindingPresenter<LoungeModel> simpleDataBindingPresenter = SimpleDataBindingPresenter.INSTANCE.get(R.layout.card_stream_item_expanded, 1);
        hoverCardPresenter = simpleDataBindingPresenter;
        FixedStartListRowPresenter fixedStartListRowPresenter = new FixedStartListRowPresenter(0, false, 2, null);
        fixedStartListRowPresenter.setHoverCardPresenterSelector(new SinglePresenterSelector(simpleDataBindingPresenter));
        presenterWithHoverCard = fixedStartListRowPresenter;
    }
}