// [jadx java-decompile failed → smali]
// target: com.stremio.common.api.StremioApi
// markers: Method not decompiled
// (Java body was an anti-decompile stub; showing smali. Pass smali_fallback=false for the partial Java.)

###### Class com.stremio.common.api.StremioApi (com.stremio.common.api.StremioApi)
.class public final Lcom/stremio/common/api/StremioApi;
.super Ljava/lang/Object;
.source "StremioApi.kt"


# annotations
.annotation system Ldalvik/annotation/SourceDebugExtension;
    value = "SMAP\nStremioApi.kt\nKotlin\n*S Kotlin\n*F\n+ 1 StremioApi.kt\ncom/stremio/common/api/StremioApi\n+ 2 Transform.kt\nkotlinx/coroutines/flow/FlowKt__TransformKt\n+ 3 Emitters.kt\nkotlinx/coroutines/flow/FlowKt__EmittersKt\n+ 4 SafeCollector.common.kt\nkotlinx/coroutines/flow/internal/SafeCollector_commonKt\n+ 5 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 6 Core.kt\ncom/stremio/core/Core\n+ 7 CancellableContinuation.kt\nkotlinx/coroutines/CancellableContinuationKt\n+ 8 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,969:1\n859#1,10:975\n869#1:990\n870#1,5:994\n859#1,10:1004\n869#1:1019\n870#1,5:1023\n859#1,10:1033\n869#1:1048\n870#1,5:1052\n859#1,10:1057\n869#1:1072\n870#1,5:1076\n859#1,10:1081\n869#1:1096\n870#1,5:1100\n859#1,10:1105\n869#1:1120\n870#1,5:1124\n859#1,10:1129\n869#1:1144\n870#1,5:1148\n859#1,10:1153\n869#1:1168\n870#1,5:1172\n859#1,10:1177\n869#1:1192\n870#1,5:1196\n859#1,10:1201\n869#1:1216\n870#1,5:1220\n859#1,10:1241\n869#1:1256\n870#1,5:1260\n859#1,10:1268\n869#1:1283\n870#1,5:1287\n859#1,10:1292\n869#1:1307\n870#1,5:1311\n859#1,10:1316\n869#1:1331\n870#1,5:1335\n859#1,10:1340\n869#1:1355\n870#1,5:1359\n859#1,10:1364\n869#1:1379\n870#1,5:1383\n859#1,10:1388\n869#1:1403\n870#1,5:1407\n49#2:970\n51#2:974\n56#2:985\n59#2:989\n17#2,3:991\n49#2:999\n51#2:1003\n56#2:1014\n59#2:1018\n17#2,3:1020\n49#2:1028\n51#2:1032\n56#2:1043\n59#2:1047\n17#2,3:1049\n56#2:1067\n59#2:1071\n17#2,3:1073\n56#2:1091\n59#2:1095\n17#2,3:1097\n56#2:1115\n59#2:1119\n17#2,3:1121\n56#2:1139\n59#2:1143\n17#2,3:1145\n56#2:1163\n59#2:1167\n17#2,3:1169\n56#2:1187\n59#2:1191\n17#2,3:1193\n56#2:1211\n59#2:1215\n17#2,3:1217\n56#2:1225\n59#2:1229\n17#2:1230\n19#2:1234\n49#2:1236\n51#2:1240\n56#2:1251\n59#2:1255\n17#2,3:1257\n56#2:1278\n59#2:1282\n17#2,3:1284\n56#2:1302\n59#2:1306\n17#2,3:1308\n56#2:1326\n59#2:1330\n17#2,3:1332\n56#2:1350\n59#2:1354\n17#2,3:1356\n56#2:1374\n59#2:1378\n17#2,3:1380\n56#2:1398\n59#2:1402\n17#2,3:1404\n56#2:1412\n59#2:1416\n17#2:1417\n19#2:1421\n56#2:1422\n59#2:1426\n17#2:1427\n19#2:1431\n45#3:971\n49#3:973\n45#3:986\n49#3:988\n45#3:1000\n49#3:1002\n45#3:1015\n49#3:1017\n45#3:1029\n49#3:1031\n45#3:1044\n49#3:1046\n45#3:1068\n49#3:1070\n45#3:1092\n49#3:1094\n45#3:1116\n49#3:1118\n45#3:1140\n49#3:1142\n45#3:1164\n49#3:1166\n45#3:1188\n49#3:1190\n45#3:1212\n49#3:1214\n45#3:1226\n49#3:1228\n45#3:1231\n49#3:1233\n45#3:1237\n49#3:1239\n45#3:1252\n49#3:1254\n45#3:1279\n49#3:1281\n45#3:1303\n49#3:1305\n45#3:1327\n49#3:1329\n45#3:1351\n49#3:1353\n45#3:1375\n49#3:1377\n45#3:1399\n49#3:1401\n45#3:1413\n49#3:1415\n45#3:1418\n49#3:1420\n45#3:1423\n49#3:1425\n45#3:1428\n49#3:1430\n105#4:972\n105#4:987\n105#4:1001\n105#4:1016\n105#4:1030\n105#4:1045\n105#4:1069\n105#4:1093\n105#4:1117\n105#4:1141\n105#4:1165\n105#4:1189\n105#4:1213\n105#4:1227\n105#4:1232\n105#4:1238\n105#4:1253\n105#4:1280\n105#4:1304\n105#4:1328\n105#4:1352\n105#4:1376\n105#4:1400\n105#4:1414\n105#4:1419\n105#4:1424\n105#4:1429\n1#5:1235\n57#6,3:1265\n433#7,10:1432\n2091#8,3:1442\n*S KotlinDebug\n*F\n+ 1 StremioApi.kt\ncom/stremio/common/api/StremioApi\n*L\n159#1:975,10\n159#1:990\n159#1:994,5\n325#1:1004,10\n325#1:1019\n325#1:1023,5\n390#1:1033,10\n390#1:1048\n390#1:1052,5\n473#1:1057,10\n473#1:1072\n473#1:1076,5\n519#1:1081,10\n519#1:1096\n519#1:1100,5\n541#1:1105,10\n541#1:1120\n541#1:1124,5\n551#1:1129,10\n551#1:1144\n551#1:1148,5\n574#1:1153,10\n574#1:1168\n574#1:1172,5\n594#1:1177,10\n594#1:1192\n594#1:1196,5\n604#1:1201,10\n604#1:1216\n604#1:1220,5\n763#1:1241,10\n763#1:1256\n763#1:1260,5\n820#1:1268,10\n820#1:1283\n820#1:1287,5\n829#1:1292,10\n829#1:1307\n829#1:1311,5\n834#1:1316,10\n834#1:1331\n834#1:1335,5\n841#1:1340,10\n841#1:1355\n841#1:1359,5\n848#1:1364,10\n848#1:1379\n848#1:1383,5\n855#1:1388,10\n855#1:1403\n855#1:1407,5\n146#1:970\n146#1:974\n159#1:985\n159#1:989\n159#1:991,3\n164#1:999\n164#1:1003\n325#1:1014\n325#1:1018\n325#1:1020,3\n328#1:1028\n328#1:1032\n390#1:1043\n390#1:1047\n390#1:1049,3\n473#1:1067\n473#1:1071\n473#1:1073,3\n519#1:1091\n519#1:1095\n519#1:1097,3\n541#1:1115\n541#1:1119\n541#1:1121,3\n551#1:1139\n551#1:1143\n551#1:1145,3\n574#1:1163\n574#1:1167\n574#1:1169,3\n594#1:1187\n594#1:1191\n594#1:1193,3\n604#1:1211\n604#1:1215\n604#1:1217,3\n618#1:1225\n618#1:1229\n619#1:1230\n619#1:1234\n736#1:1236\n736#1:1240\n763#1:1251\n763#1:1255\n763#1:1257,3\n820#1:1278\n820#1:1282\n820#1:1284,3\n829#1:1302\n829#1:1306\n829#1:1308,3\n834#1:1326\n834#1:1330\n834#1:1332,3\n841#1:1350\n841#1:1354\n841#1:1356,3\n848#1:1374\n848#1:1378\n848#1:1380,3\n855#1:1398\n855#1:1402\n855#1:1404,3\n868#1:1412\n868#1:1416\n869#1:1417\n869#1:1421\n868#1:1422\n868#1:1426\n869#1:1427\n869#1:1431\n146#1:971\n146#1:973\n159#1:986\n159#1:988\n164#1:1000\n164#1:1002\n325#1:1015\n325#1:1017\n328#1:1029\n328#1:1031\n390#1:1044\n390#1:1046\n473#1:1068\n473#1:1070\n519#1:1092\n519#1:1094\n541#1:1116\n541#1:1118\n551#1:1140\n551#1:1142\n574#1:1164\n574#1:1166\n594#1:1188\n594#1:1190\n604#1:1212\n604#1:1214\n618#1:1226\n618#1:1228\n619#1:1231\n619#1:1233\n736#1:1237\n736#1:1239\n763#1:1252\n763#1:1254\n820#1:1279\n820#1:1281\n829#1:1303\n829#1:1305\n834#1:1327\n834#1:1329\n841#1:1351\n841#1:1353\n848#1:1375\n848#1:1377\n855#1:1399\n855#1:1401\n868#1:1413\n868#1:1415\n869#1:1418\n869#1:1420\n868#1:1423\n868#1:1425\n869#1:1428\n869#1:1430\n146#1:972\n159#1:987\n164#1:1001\n325#1:1016\n328#1:1030\n390#1:1045\n473#1:1069\n519#1:1093\n541#1:1117\n551#1:1141\n574#1:1165\n594#1:1189\n604#1:1213\n618#1:1227\n619#1:1232\n736#1:1238\n763#1:1253\n820#1:1280\n829#1:1304\n834#1:1328\n841#1:1352\n848#1:1376\n855#1:1400\n868#1:1414\n869#1:1419\n868#1:1424\n869#1:1429\n815#1:1265,3\n895#1:1432,10\n938#1:1442,3\n*E\n"
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000\u00e6\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0002\u0008\n\n\u0002\u0010\u000b\n\u0002\u0008\u0004\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0008\n\u0002\u0008\u0005\n\u0002\u0018\u0002\n\u0002\u0008\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0004\n\u0002\u0018\u0002\n\u0002\u0008\u0004\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0005\n\u0002\u0018\u0002\n\u0002\u0008\u000f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0007\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0005\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\u0008\u0007\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0002\u0008\u0006\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0002\u0008\u0004\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0008\u0007\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0004\u0008\u0008\u0010\tJ\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u000bH\u0086@\u00a2\u0006\u0002\u0010\u0016J\u0008\u0010\u0017\u001a\u00020\u0018H\u0002J\u0016\u0010\u0019\u001a\u0008\u0012\u0004\u0012\u00020\u001b0\u001aH\u0086@\u00a2\u0006\u0004\u0008\u001c\u0010\u0016J\u000c\u0010\u001d\u001a\u0008\u0012\u0004\u0012\u00020\u001f0\u001eJ\u0016\u0010 \u001a\u0008\u0012\u0004\u0012\u00020!0\u001aH\u0086@\u00a2\u0006\u0004\u0008\"\u0010\u0016J\u001e\u0010#\u001a\u0008\u0012\u0004\u0012\u00020$0\u001a2\u0006\u0010%\u001a\u00020\u0003H\u0086@\u00a2\u0006\u0004\u0008&\u0010\'J\u001e\u0010(\u001a\u0008\u0012\u0004\u0012\u00020$0\u001a2\u0006\u0010)\u001a\u00020\u0003H\u0086@\u00a2\u0006\u0004\u0008*\u0010\'J.\u0010+\u001a\u0008\u0012\u0004\u0012\u00020$0\u001a2\u0006\u0010,\u001a\u00020\u00032\u0006\u0010-\u001a\u00020\u00032\u0006\u0010.\u001a\u00020/H\u0086@\u00a2\u0006\u0004\u00080\u00101J.\u00102\u001a\u0008\u0012\u0004\u0012\u00020$0\u001a2\u0006\u0010,\u001a\u00020\u00032\u0006\u0010-\u001a\u00020\u00032\u0006\u00103\u001a\u000204H\u0086@\u00a2\u0006\u0004\u00085\u00106J\u0016\u00107\u001a\u0008\u0012\u0004\u0012\u0002080\u001aH\u0086@\u00a2\u0006\u0004\u00089\u0010\u0016J\u001e\u0010:\u001a\u0008\u0012\u0004\u0012\u00020;0\u001a2\u0006\u0010-\u001a\u00020\u0003H\u0086@\u00a2\u0006\u0004\u0008<\u0010\'J\u0016\u0010=\u001a\u0008\u0012\u0004\u0012\u00020>0\u001aH\u0086@\u00a2\u0006\u0004\u0008?\u0010\u0016J\u0016\u0010@\u001a\u0008\u0012\u0004\u0012\u00020A0\u001aH\u0086@\u00a2\u0006\u0004\u0008B\u0010\u0016J\u0016\u0010C\u001a\u0008\u0012\u0004\u0012\u00020D0\u001aH\u0086@\u00a2\u0006\u0004\u0008E\u0010\u0016J\u0016\u0010F\u001a\u0008\u0012\u0004\u0012\u00020G0\u001aH\u0086@\u00a2\u0006\u0004\u0008H\u0010\u0016J\u0006\u0010I\u001a\u00020\u0018J\u0006\u0010J\u001a\u00020\u0018J\u001e\u0010K\u001a\u0008\u0012\u0004\u0012\u00020L0\u001a2\u0006\u0010M\u001a\u00020NH\u0086@\u00a2\u0006\u0004\u0008O\u0010PJ\u001e\u0010Q\u001a\u0008\u0012\u0004\u0012\u00020R0\u001a2\u0006\u0010S\u001a\u00020\u0003H\u0086@\u00a2\u0006\u0004\u0008T\u0010\'J\u001e\u0010U\u001a\u0008\u0012\u0004\u0012\u00020V0\u001a2\u0006\u0010W\u001a\u00020XH\u0086@\u00a2\u0006\u0004\u0008Y\u0010ZJ\u001e\u0010[\u001a\u0008\u0012\u0004\u0012\u00020\\0\u001a2\u0006\u0010W\u001a\u00020XH\u0086@\u00a2\u0006\u0004\u0008]\u0010ZJ\u0016\u0010^\u001a\u00020\u00182\u0006\u0010_\u001a\u00020`2\u0006\u0010a\u001a\u00020bJ\u0016\u0010c\u001a\u00020\u00182\u0006\u0010_\u001a\u00020`2\u0006\u0010d\u001a\u00020/J\u0018\u0010e\u001a\u00020\u00182\u0006\u0010_\u001a\u00020`2\u0008\u0010f\u001a\u0004\u0018\u00010\u0003J&\u0010g\u001a\u0008\u0012\u0004\u0012\u00020h0\u001a2\u0006\u0010i\u001a\u00020\u00032\u0006\u0010j\u001a\u00020/H\u0086@\u00a2\u0006\u0004\u0008k\u0010lJ\u000e\u0010m\u001a\u00020\u00182\u0006\u0010n\u001a\u00020\u0003J\u000e\u0010o\u001a\u00020\u00182\u0006\u0010p\u001a\u00020\u0003J\u000e\u0010q\u001a\u00020\u00182\u0006\u0010_\u001a\u00020\u0003J\u000e\u0010r\u001a\u00020\u00182\u0006\u0010s\u001a\u00020\u0003J\u0006\u0010t\u001a\u00020\u0018J\u000e\u0010u\u001a\u00020\u00182\u0006\u0010S\u001a\u00020\u0003J\u000e\u0010v\u001a\u00020\u00182\u0006\u0010S\u001a\u00020\u0003J\u000e\u0010w\u001a\u00020\u00182\u0006\u0010S\u001a\u00020\u0003J\u000e\u0010x\u001a\u00020\u00182\u0006\u0010S\u001a\u00020\u0003J\u0014\u0010y\u001a\u00020\u00182\n\u0010z\u001a\u0006\u0012\u0002\u0008\u00030{H\u0002J\u000c\u0010|\u001a\u0008\u0012\u0004\u0012\u00020}0\u001eJ\u0016\u0010~\u001a\u00020\u00182\u0006\u0010i\u001a\u00020\u00032\u0006\u0010\u007f\u001a\u00020/J\u0019\u0010\u0080\u0001\u001a\u00020\u00182\u0008\u0010\u0081\u0001\u001a\u00030\u0082\u00012\u0006\u0010\u007f\u001a\u00020/J\u0018\u0010\u0083\u0001\u001a\u00020\u00182\u0007\u0010\u0084\u0001\u001a\u00020b2\u0006\u0010\u007f\u001a\u00020/J\u0013\u0010\u0085\u0001\u001a\u00020\u00182\n\u0010\u0086\u0001\u001a\u0005\u0018\u00010\u0087\u0001J\u0019\u0010\u0088\u0001\u001a\u00020\u00182\u0008\u0010\u0081\u0001\u001a\u00030\u0082\u00012\u0006\u0010\u007f\u001a\u00020/J\u0018\u0010\u0089\u0001\u001a\u00020\u00182\u0007\u0010\u0084\u0001\u001a\u00020b2\u0006\u0010\u007f\u001a\u00020/J$\u0010\u008a\u0001\u001a\t\u0012\u0005\u0012\u00030\u008b\u00010\u001a2\u0008\u0010\u008c\u0001\u001a\u00030\u008d\u0001H\u0086@\u00a2\u0006\u0006\u0008\u008e\u0001\u0010\u008f\u0001J$\u0010\u0090\u0001\u001a\t\u0012\u0005\u0012\u00030\u0091\u00010\u001a2\u0008\u0010\u0092\u0001\u001a\u00030\u0093\u0001H\u0086@\u00a2\u0006\u0006\u0008\u0094\u0001\u0010\u0095\u0001J$\u0010\u0096\u0001\u001a\t\u0012\u0005\u0012\u00030\u0097\u00010\u001a2\u0008\u0010\u0092\u0001\u001a\u00030\u0093\u0001H\u0086@\u00a2\u0006\u0006\u0008\u0098\u0001\u0010\u0095\u0001J$\u0010\u0099\u0001\u001a\t\u0012\u0005\u0012\u00030\u009a\u00010\u001a2\u0008\u0010\u0092\u0001\u001a\u00030\u0093\u0001H\u0086@\u00a2\u0006\u0006\u0008\u009b\u0001\u0010\u0095\u0001J\u001d\u0010\u009c\u0001\u001a\u00020\u00182\t\u0010\u009d\u0001\u001a\u0004\u0018\u00010\u00032\t\u0010\u009e\u0001\u001a\u0004\u0018\u00010\u0003J\u0017\u0010\u009f\u0001\u001a\t\u0012\u0005\u0012\u00030\u00a0\u00010\u001e2\u0007\u0010\u009d\u0001\u001a\u00020\u0003J@\u0010\u00a1\u0001\u001a\u00020\u00182\u0007\u0010\u00a2\u0001\u001a\u00020/2\u0007\u0010\u00a3\u0001\u001a\u00020/2\t\u0010\u00a4\u0001\u001a\u0004\u0018\u00010\u00032\t\u0010\u009e\u0001\u001a\u0004\u0018\u00010\u00032\t\u0010\u00a5\u0001\u001a\u0004\u0018\u00010b\u00a2\u0006\u0003\u0010\u00a6\u0001JB\u0010\u00a7\u0001\u001a\u00020\u00182\u0007\u0010\u009d\u0001\u001a\u00020\u00032\t\u0010\u00a3\u0001\u001a\u0004\u0018\u00010/2\t\u0010\u00a4\u0001\u001a\u0004\u0018\u00010\u00032\t\u0010\u009e\u0001\u001a\u0004\u0018\u00010\u00032\t\u0010\u00a5\u0001\u001a\u0004\u0018\u00010b\u00a2\u0006\u0003\u0010\u00a8\u0001J\u0010\u0010\u00a9\u0001\u001a\u00020\u00182\u0007\u0010\u009d\u0001\u001a\u00020\u0003J\u001a\u0010\u00aa\u0001\u001a\u00020\u00182\u0007\u0010\u009d\u0001\u001a\u00020\u00032\u0008\u0010\u0092\u0001\u001a\u00030\u0093\u0001J\u001a\u0010\u00ab\u0001\u001a\u00020\u00182\u0007\u0010\u009d\u0001\u001a\u00020\u00032\u0008\u0010\u0092\u0001\u001a\u00030\u0093\u0001J \u0010\u00ac\u0001\u001a\u00020\u00182\u0007\u0010\u009d\u0001\u001a\u00020\u00032\u0006\u0010_\u001a\u00020`2\u0006\u0010a\u001a\u00020bJ \u0010\u00ad\u0001\u001a\u00020\u00182\u0007\u0010\u009d\u0001\u001a\u00020\u00032\u0006\u0010_\u001a\u00020`2\u0006\u0010d\u001a\u00020/J\"\u0010\u00ae\u0001\u001a\u00020\u00182\u0007\u0010\u009d\u0001\u001a\u00020\u00032\u0006\u0010_\u001a\u00020`2\u0008\u0010f\u001a\u0004\u0018\u00010\u0003J\u000e\u0010\u00af\u0001\u001a\t\u0012\u0005\u0012\u00030\u00b0\u00010\u001eJ\u000e\u0010\u00b1\u0001\u001a\t\u0012\u0005\u0012\u00030\u00b2\u00010\u001eJ\u0017\u0010\u00b3\u0001\u001a\t\u0012\u0005\u0012\u00030\u00b2\u00010\u001e2\u0007\u0010\u00b4\u0001\u001a\u00020\u0003J\u0007\u0010\u00b5\u0001\u001a\u00020\u0018J#\u0010\u00b6\u0001\u001a\u00020\u00182\u0007\u0010\u00b7\u0001\u001a\u00020b2\u0007\u0010\u00b8\u0001\u001a\u00020b2\u0008\u0010\u00b9\u0001\u001a\u00030\u00ba\u0001J\u0010\u0010\u00bb\u0001\u001a\u00020\u00182\u0007\u0010\u00bc\u0001\u001a\u00020bJ\u001f\u0010\u00bd\u0001\u001a\t\u0012\u0004\u0012\u00020N0\u00be\u00012\u0007\u0010\u00b4\u0001\u001a\u00020\u0003H\u0086@\u00a2\u0006\u0002\u0010\'J\u000e\u0010\u00bf\u0001\u001a\t\u0012\u0005\u0012\u00030\u00c0\u00010\u001eJ\u0011\u0010\u00c1\u0001\u001a\u00020\u00182\u0008\u0010\u00c2\u0001\u001a\u00030\u00c3\u0001J\u0007\u0010\u00c4\u0001\u001a\u00020\u0018J\u000e\u0010\u00c5\u0001\u001a\t\u0012\u0005\u0012\u00030\u00c6\u00010\u001eJ\u0011\u0010\u00c7\u0001\u001a\u00020\u00182\u0008\u0010\u00c8\u0001\u001a\u00030\u00c9\u0001J\u000e\u0010\u00ca\u0001\u001a\t\u0012\u0005\u0012\u00030\u00c0\u00010\u001eJ\u0019\u0010\u00cb\u0001\u001a\u00030\u00c0\u00012\u0007\u0010\u00cc\u0001\u001a\u00020\u0003H\u0086@\u00a2\u0006\u0002\u0010\'J\u0011\u0010\u00cd\u0001\u001a\u00020\u00182\u0008\u0010\u00c2\u0001\u001a\u00030\u00c3\u0001J\u0007\u0010\u00ce\u0001\u001a\u00020\u0018J\u000e\u0010\u00cf\u0001\u001a\t\u0012\u0005\u0012\u00030\u00d0\u00010\u001eJ\u0011\u0010\u00d1\u0001\u001a\u00020\u00182\u0008\u0010\u00c2\u0001\u001a\u00030\u00d2\u0001J\u0007\u0010\u00d3\u0001\u001a\u00020\u0018J\u000e\u0010\u00d4\u0001\u001a\t\u0012\u0005\u0012\u00030\u00d5\u00010\u001eJ\u0011\u0010\u00d6\u0001\u001a\u00020\u00182\u0008\u0010\u00c2\u0001\u001a\u00030\u00d2\u0001J\u0017\u0010\u00d7\u0001\u001a\t\u0012\u0005\u0012\u00030\u00d8\u00010\u001e2\u0007\u0010\u00d9\u0001\u001a\u00020\u0003J\u0018\u0010\u00da\u0001\u001a\u00020/2\u0007\u0010\u00cc\u0001\u001a\u00020\u00032\u0006\u0010S\u001a\u00020\u0003J\'\u0010\u00db\u0001\u001a\u0008\u0012\u0004\u0012\u00020\u00100\u001e2\u0007\u0010\u00cc\u0001\u001a\u00020\u00032\u0006\u0010S\u001a\u00020\u0003H\u0086@\u00a2\u0006\u0003\u0010\u00dc\u0001J7\u0010\u00dd\u0001\u001a\t\u0012\u0005\u0012\u00030\u00de\u00010\u001e2\u0007\u0010\u00cc\u0001\u001a\u00020\u00032\u0006\u0010S\u001a\u00020\u00032\u000b\u0008\u0002\u0010\u00df\u0001\u001a\u0004\u0018\u00010\u00032\t\u0008\u0002\u0010\u00e0\u0001\u001a\u00020/JN\u0010\u00e1\u0001\u001a\t\u0012\u0005\u0012\u00030\u00e2\u00010\u001e2\u0008\u0010\u00e3\u0001\u001a\u00030\u00e4\u00012\t\u0010\u00e5\u0001\u001a\u0004\u0018\u00010\u00032\t\u0010\u00e6\u0001\u001a\u0004\u0018\u00010\u00032\t\u0010\u00cc\u0001\u001a\u0004\u0018\u00010\u00032\u0008\u0010S\u001a\u0004\u0018\u00010\u00032\t\u0010\u00df\u0001\u001a\u0004\u0018\u00010\u0003J\u0018\u0010\u00e7\u0001\u001a\t\u0012\u0005\u0012\u00030\u00e2\u00010\u001e2\u0008\u0010\u00e8\u0001\u001a\u00030\u00e9\u0001J\u0010\u0010\u00ea\u0001\u001a\u00020\u00182\u0007\u0010d\u001a\u00030\u00eb\u0001J\u001a\u0010\u00ec\u0001\u001a\u00020\u00182\u0007\u0010a\u001a\u00030\u00ed\u00012\u0008\u0010\u00ee\u0001\u001a\u00030\u00ed\u0001J\u001a\u0010\u00ef\u0001\u001a\u00020\u00182\u0007\u0010a\u001a\u00030\u00ed\u00012\u0008\u0010\u00ee\u0001\u001a\u00030\u00ed\u0001J\u0010\u0010\u00f0\u0001\u001a\u00020\u00182\u0007\u0010\u00f1\u0001\u001a\u00020/J\u0007\u0010\u00f2\u0001\u001a\u00020\u0018J\u0007\u0010\u00f3\u0001\u001a\u00020\u0018J\u0017\u0010\u00f4\u0001\u001a\t\u0012\u0005\u0012\u00030\u00f5\u00010\u001e2\u0007\u0010\u00f6\u0001\u001a\u00020\u0003J\u0011\u0010\u00f7\u0001\u001a\u00020\u00182\u0008\u0010\u00f8\u0001\u001a\u00030\u00f9\u0001J!\u0010\u00fa\u0001\u001a\u00020\u00182\u0007\u0010\u00fb\u0001\u001a\u00020\u00032\t\u0010\u00fc\u0001\u001a\u0004\u0018\u00010b\u00a2\u0006\u0003\u0010\u00fd\u0001J\u0007\u0010\u00fe\u0001\u001a\u00020\u0018J\u0010\u0010\u00ff\u0001\u001a\u00030\u0080\u0002H\u0086@\u00a2\u0006\u0002\u0010\u0016J\u000e\u0010\u0081\u0002\u001a\t\u0012\u0005\u0012\u00030\u0080\u00020\u001eJ\u001b\u0010\u0082\u0002\u001a\u00030\u0080\u00022\u0008\u0010\u008c\u0001\u001a\u00030\u0083\u0002H\u0086@\u00a2\u0006\u0003\u0010\u0084\u0002J\u0011\u0010\u0085\u0002\u001a\u00020\u00182\u0008\u0010\u00b9\u0001\u001a\u00030\u00ba\u0001J\u0012\u0010\u0086\u0002\u001a\r\u0012\t\u0012\u0007\u0012\u0002\u0008\u00030\u0087\u00020\u001eJ\u000e\u0010\u0088\u0002\u001a\t\u0012\u0005\u0012\u00030\u0089\u00020\u001eJ\u000e\u0010\u008a\u0002\u001a\t\u0012\u0005\u0012\u00030\u008b\u00020\u001eJ+\u0010\u008c\u0002\u001a\u0005\u0018\u0001H\u008d\u0002\"\u000c\u0008\u0000\u0010\u008d\u0002\u0018\u0001*\u00030\u008e\u00022\u0008\u0010\u00b9\u0001\u001a\u00030\u00ba\u0001H\u0086H\u00a2\u0006\u0003\u0010\u008f\u0002J+\u0010\u0090\u0002\u001a\t\u0012\u0005\u0012\u00030\u00b2\u00010\u001e2\u000f\u0010\u0091\u0002\u001a\n\u0012\u0005\u0012\u00030\u0092\u00020\u00be\u00012\u0008\u0010\u00b9\u0001\u001a\u00030\u00ba\u0001H\u0002J$\u0010\u00ca\u0001\u001a\t\u0012\u0005\u0012\u00030\u00c0\u00010\u001e2\u0008\u0010\u00c2\u0001\u001a\u00030\u00c3\u00012\u0008\u0010\u00b9\u0001\u001a\u00030\u00ba\u0001H\u0002J\u001a\u0010\u00dd\u0001\u001a\t\u0012\u0005\u0012\u00030\u00de\u00010\u001e2\u0008\u0010\u00c2\u0001\u001a\u00030\u0093\u0002H\u0002J\u001c\u0010\u00ff\u0001\u001a\t\u0012\u0005\u0012\u00030\u0080\u00020\u001e2\n\u0010z\u001a\u0006\u0012\u0002\u0008\u00030{H\u0002J\u0010\u0010\u0094\u0002\u001a\t\u0012\u0005\u0012\u00030\u00e2\u00010\u001eH\u0002J\u001d\u0010\u0095\u0002\u001a\t\u0012\u0005\u0012\u00030\u0096\u00020\u001e2\u000b\u0010z\u001a\u0007\u0012\u0002\u0008\u00030\u0097\u0002H\u0002Jy\u0010\u0098\u0002\u001a\t\u0012\u0005\u0012\u0003H\u008d\u00020\u001e\"\u000c\u0008\u0000\u0010\u008d\u0002\u0018\u0001*\u00030\u008e\u00022\u0008\u0010\u00b9\u0001\u001a\u00030\u00ba\u00012\u0017\u0008\u0006\u0010\u0099\u0002\u001a\u0010\u0012\u0005\u0012\u0003H\u008d\u0002\u0012\u0004\u0012\u00020/0\u009a\u00022\u0017\u0008\u0006\u0010\u009b\u0002\u001a\u0010\u0012\u0005\u0012\u0003H\u008d\u0002\u0012\u0004\u0012\u00020/0\u009a\u00022\t\u0008\u0002\u0010\u009c\u0002\u001a\u00020/2\u0011\u0008\u0002\u0010\u009d\u0002\u001a\n\u0012\u0005\u0012\u00030\u00ba\u00010\u009e\u0002H\u0082\u0008J6\u0010\u009f\u0002\u001a\t\u0012\u0005\u0012\u00030\u00a0\u00020\u001e2\u0008\u0010\u00b9\u0001\u001a\u00030\u00ba\u00012\u0007\u0010\u009c\u0002\u001a\u00020/2\u0011\u0008\u0002\u0010\u009d\u0002\u001a\n\u0012\u0005\u0012\u00030\u00ba\u00010\u009e\u0002H\u0002JH\u0010\u00a1\u0002\u001a\t\u0012\u0005\u0012\u0003H\u008d\u00020\u001a\"\u000e\u0008\u0000\u0010\u008d\u0002*\u0007\u0012\u0002\u0008\u00030\u0087\u00022\u000b\u0010z\u001a\u0007\u0012\u0002\u0008\u00030\u00a2\u00022\u000f\u0010\u00cc\u0001\u001a\n\u0012\u0005\u0012\u0003H\u008d\u00020\u00a3\u0002H\u0082@\u00a2\u0006\u0006\u0008\u00a4\u0002\u0010\u00a5\u0002J\u0016\u0010\u00a6\u0002\u001a\u00020\u00182\u000b\u0010z\u001a\u0007\u0012\u0002\u0008\u00030\u00a7\u0002H\u0002J \u0010\u00a8\u0002\u001a\u00020\u00182\u000b\u0010z\u001a\u0007\u0012\u0002\u0008\u00030\u00a9\u00022\u0008\u0010\u00b9\u0001\u001a\u00030\u00ba\u0001H\u0002J\u0013\u0010\u00aa\u0002\u001a\u00020\u00182\u0008\u0010\u00b9\u0001\u001a\u00030\u00ba\u0001H\u0002J\"\u0010\u00ab\u0002\u001a\u00020\u00182\u000b\u0010z\u001a\u0007\u0012\u0002\u0008\u00030\u00a2\u00022\n\u0008\u0002\u0010\u00b9\u0001\u001a\u00030\u00ba\u0001H\u0002J\u0016\u0010\u00ac\u0002\u001a\u00020\u00182\u000b\u0010z\u001a\u0007\u0012\u0002\u0008\u00030\u00ad\u0002H\u0002J \u0010\u00ae\u0002\u001a\u00020\u00182\u000b\u0010z\u001a\u0007\u0012\u0002\u0008\u00030\u0097\u00022\u0008\u0010\u00b9\u0001\u001a\u00030\u00ba\u0001H\u0002J+\u0010\u00af\u0002\u001a\u0005\u0018\u0001H\u008d\u0002\"\u000c\u0008\u0000\u0010\u008d\u0002\u0018\u0001*\u00030\u008e\u00022\u0008\u0010\u00b9\u0001\u001a\u00030\u00ba\u0001H\u0082H\u00a2\u0006\u0003\u0010\u008f\u0002J2\u0010\u00b0\u0002\u001a\u00030\u00b1\u00022\u000f\u0010\u00b2\u0002\u001a\n\u0012\u0005\u0012\u00030\u00ba\u00010\u009e\u00022\u0015\u0010\u00b3\u0002\u001a\u0010\u0012\u0005\u0012\u00030\u00a0\u0002\u0012\u0004\u0012\u00020\u00180\u009a\u0002H\u0002JB\u0010\u00b4\u0002\u001a\u00030\u00b1\u0002\"\u000e\u0008\u0000\u0010\u008d\u0002*\u0007\u0012\u0002\u0008\u00030\u0087\u00022\u000f\u0010\u00cc\u0001\u001a\n\u0012\u0005\u0012\u0003H\u008d\u00020\u00a3\u00022\u0015\u0010\u00b5\u0002\u001a\u0010\u0012\u000b\u0012\t\u0012\u0005\u0012\u0003H\u008d\u00020\u001a0\u00b6\u0002H\u0002J!\u0010\u00b7\u0002\u001a\u00020\u0018\"\u0005\u0008\u0000\u0010\u008d\u00022\u000f\u0010\u00b8\u0002\u001a\n\u0012\u0005\u0012\u0003H\u008d\u00020\u00b9\u0002H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000c\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00100\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0012\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008\u0013\u0010\u0014\u00ca\u0001\u000e\u0008\u00bb\u0002\u0012\t\u0008\u00bc\u0002\u0012\u0004\u0008\u0003\u0010\u0000\u00a8\u0006\u00ba\u0002"
    }
    d2 = {
        "Lcom/stremio/common/api/StremioApi;",
        "",
        "cachePath",
        "",
        "storage",
        "Lcom/stremio/core/Storage;",
        "quickSearchApi",
        "Lcom/stremio/common/api/QuickSearchApi;",
        "<init>",
        "(Ljava/lang/String;Lcom/stremio/core/Storage;Lcom/stremio/common/api/QuickSearchApi;)V",
        "error",
        "Lcom/stremio/core/runtime/EnvError;",
        "dispatchScope",
        "Lkotlinx/coroutines/CoroutineScope;",
        "metaItemCache",
        "Landroid/util/LruCache;",
        "Lcom/stremio/core/types/resource/MetaItem;",
        "loadCore",
        "Lkotlinx/coroutines/Deferred;",
        "getLoadCore",
        "()Lkotlinx/coroutines/Deferred;",
        "load",
        "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;",
        "startAnalyticsPublishing",
        "",
        "createLinkCode",
        "Lkotlin/Result;",
        "Lcom/stremio/core/types/api/LinkCodeResponse;",
        "createLinkCode-IoAF18A",
        "ctx",
        "Lkotlinx/coroutines/flow/Flow;",
        "Lcom/stremio/core/models/Ctx;",
        "readLinkCode",
        "Lcom/stremio/core/types/api/LinkAuthKey;",
        "readLinkCode-IoAF18A",
        "authWithFacebook",
        "Lcom/stremio/core/runtime/msg/Event$Type$UserAuthenticated;",
        "accessToken",
        "authWithFacebook-gIAlu-s",
        "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;",
        "loginWithToken",
        "authKey",
        "loginWithToken-gIAlu-s",
        "login",
        "username",
        "password",
        "isFacebook",
        "",
        "login-BWLJW6A",
        "(Ljava/lang/String;Ljava/lang/String;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;",
        "signup",
        "gdprConsent",
        "Lcom/stremio/core/types/profile/GDPRConsent;",
        "signup-BWLJW6A",
        "(Ljava/lang/String;Ljava/lang/String;Lcom/stremio/core/types/profile/GDPRConsent;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;",
        "logout",
        "Lcom/stremio/core/runtime/msg/Event$Type$UserLoggedOut;",
        "logout-IoAF18A",
        "deleteAccount",
        "Lcom/stremio/core/runtime/msg/Event$Type$UserAccountDeleted;",
        "deleteAccount-gIAlu-s",
        "traktLogout",
        "Lcom/stremio/core/runtime/msg/Event$Type$TraktLoggedOut;",
        "traktLogout-IoAF18A",
        "traktInstallAddon",
        "Lcom/stremio/core/runtime/msg/Event$Type$TraktAddonFetched;",
        "traktInstallAddon-IoAF18A",
        "syncLibrary",
        "Lcom/stremio/core/runtime/msg/Event$Type$LibrarySyncWithApiPlanned;",
        "syncLibrary-IoAF18A",
        "syncAddons",
        "Lcom/stremio/core/runtime/msg/Event$Type$AddonsPulledFromApi;",
        "syncAddons-IoAF18A",
        "pullNotifications",
        "pullUserFromApi",
        "addLibraryItem",
        "Lcom/stremio/core/runtime/msg/Event$Type$LibraryItemAdded;",
        "metaItemPreview",
        "Lcom/stremio/core/types/resource/MetaItemPreview;",
        "addLibraryItem-gIAlu-s",
        "(Lcom/stremio/core/types/resource/MetaItemPreview;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;",
        "removeLibraryItem",
        "Lcom/stremio/core/runtime/msg/Event$Type$LibraryItemRemoved;",
        "id",
        "removeLibraryItem-gIAlu-s",
        "rewindLibraryItem",
        "Lcom/stremio/core/runtime/msg/Event$Type$LibraryItemRewinded;",
        "item",
        "Lcom/stremio/core/types/library/LibraryItem;",
        "rewindLibraryItem-gIAlu-s",
        "(Lcom/stremio/core/types/library/LibraryItem;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;",
        "dismissNotifications",
        "Lcom/stremio/core/runtime/msg/Event$Type$NotificationsDismissed;",
        "dismissNotifications-gIAlu-s",
        "updateContentPosition",
        "key",
        "Lcom/stremio/core/types/ContentSettingsKey;",
        "position",
        "",
        "updateContentVisibility",
        "state",
        "updateContentTitle",
        "title",
        "toggleNotifications",
        "Lcom/stremio/core/runtime/msg/Event$Type$LibraryItemNotificationsToggled;",
        "metaId",
        "toggle",
        "toggleNotifications-0E7RQCE",
        "(Ljava/lang/String;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;",
        "dismissEvent",
        "eventId",
        "selectStreamPreset",
        "presetId",
        "setIpcKey",
        "addDownload",
        "url",
        "getDownloads",
        "getDownloadById",
        "pauseDownload",
        "resumeDownload",
        "removeDownload",
        "dispatchStreamingServerAction",
        "action",
        "Lcom/stremio/core/runtime/msg/ActionStreamingServer$Args;",
        "events",
        "Lcom/stremio/core/models/Events;",
        "markAsWatched",
        "watched",
        "markVideoAsWatched",
        "video",
        "Lcom/stremio/core/types/resource/Video;",
        "markSeasonAsWatched",
        "season",
        "rate",
        "rating",
        "Lstremio/core/types/Rating;",
        "playerMarkVideoAsWatched",
        "playerMarkSeasonAsWatched",
        "updateSettings",
        "Lcom/stremio/core/runtime/msg/Event$Type$SettingsUpdated;",
        "settings",
        "Lcom/stremio/core/types/profile/Profile$Settings;",
        "updateSettings-gIAlu-s",
        "(Lcom/stremio/core/types/profile/Profile$Settings;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;",
        "installAddon",
        "Lcom/stremio/core/runtime/msg/Event$Type$AddonInstalled;",
        "descriptor",
        "Lcom/stremio/core/types/addon/AddonDescriptor;",
        "installAddon-gIAlu-s",
        "(Lcom/stremio/core/types/addon/AddonDescriptor;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;",
        "upgradeAddon",
        "Lcom/stremio/core/runtime/msg/Event$Type$AddonUpgraded;",
        "upgradeAddon-gIAlu-s",
        "uninstallAddon",
        "Lcom/stremio/core/runtime/msg/Event$Type$AddonUninstalled;",
        "uninstallAddon-gIAlu-s",
        "switchProfile",
        "profileId",
        "pin",
        "profilesManager",
        "Lcom/stremio/core/models/ProfilesManager;",
        "createProfile",
        "cloneAddons",
        "canManageAddons",
        "name",
        "avatar",
        "(ZZLjava/lang/String;Ljava/lang/String;Ljava/lang/Integer;)V",
        "updateProfile",
        "(Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;)V",
        "deleteProfile",
        "installAddonForProfile",
        "uninstallAddonForProfile",
        "updateContentPositionForProfile",
        "updateContentVisibilityForProfile",
        "updateContentTitleForProfile",
        "continueWatchingPreview",
        "Lcom/stremio/core/models/ContinueWatchingPreview;",
        "board",
        "Lcom/stremio/core/models/CatalogsWithExtra;",
        "search",
        "query",
        "clearSearchHistory",
        "loadCatalogRows",
        "start",
        "end",
        "field",
        "Lcom/stremio/core/Field;",
        "loadNextBoardCatalogPage",
        "catalogIndex",
        "quickSearch",
        "",
        "continueWatching",
        "Lcom/stremio/core/models/LibraryWithFilters;",
        "loadContinueWatching",
        "request",
        "Lcom/stremio/core/models/LibraryWithFilters$LibraryRequest;",
        "loadNextContinueWatchingPage",
        "libraryByType",
        "Lcom/stremio/core/models/LibraryByType;",
        "loadLibraryByType",
        "sort",
        "Lcom/stremio/core/models/LibraryWithFilters$Sort;",
        "libraryWithFilters",
        "loadLibraryWithFiltersType",
        "type",
        "loadLibraryWithFilters",
        "loadNextLibraryWithFiltersPage",
        "discover",
        "Lcom/stremio/core/models/CatalogWithFilters;",
        "loadDiscoverCatalog",
        "Lcom/stremio/core/types/addon/ResourceRequest;",
        "loadNextDiscoverCatalogPage",
        "addons",
        "Lcom/stremio/core/models/AddonsWithFilters;",
        "loadAddons",
        "addonDetails",
        "Lcom/stremio/core/models/AddonDetails;",
        "transportUrl",
        "hasMetaItemCached",
        "metaItem",
        "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;",
        "metaDetails",
        "Lcom/stremio/core/models/MetaDetails;",
        "videoId",
        "guessStreamPath",
        "loadPlayer",
        "Lcom/stremio/core/models/Player;",
        "stream",
        "Lcom/stremio/core/types/resource/Stream;",
        "streamAddonUrl",
        "metaAddonUrl",
        "loadPlayerSubtitles",
        "videoParams",
        "Lcom/stremio/core/models/Player$VideoParams;",
        "playerStreamStateChanged",
        "Lcom/stremio/core/models/Player$StreamState;",
        "playerSeek",
        "",
        "duration",
        "playerTimeChanged",
        "playerPausedChanged",
        "paused",
        "playerEnded",
        "playerNextVideo",
        "createMagnetLink",
        "Lcom/stremio/core/models/LoadableTramvai;",
        "magnetLink",
        "createTorrent",
        "data",
        "Lpbandk/ByteArr;",
        "getStatistics",
        "infoHash",
        "fileIndex",
        "(Ljava/lang/String;Ljava/lang/Integer;)V",
        "reloadStreamingServer",
        "streamingServer",
        "Lcom/stremio/core/models/StreamingServer;",
        "server",
        "updateStreamingServerSettings",
        "Lcom/stremio/core/models/StreamingServer$Settings;",
        "(Lcom/stremio/core/models/StreamingServer$Settings;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;",
        "clearState",
        "coreEvents",
        "Lcom/stremio/core/runtime/msg/Event$Type;",
        "errors",
        "Lcom/stremio/core/runtime/msg/Event$Error;",
        "addonPulledFromApiEvents",
        "Lcom/stremio/core/runtime/msg/Event$AddonsPulledFromAPI;",
        "initialState",
        "T",
        "Lpbandk/Message;",
        "(Lcom/stremio/core/Field;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;",
        "catalogsWithExtra",
        "extras",
        "Lcom/stremio/core/types/addon/ExtraValue;",
        "Lcom/stremio/core/models/MetaDetails$Selected;",
        "player",
        "authLinkAction",
        "Lcom/stremio/core/models/AuthLink;",
        "Lcom/stremio/core/runtime/msg/Action$Type;",
        "fieldStateFlow",
        "sendCondition",
        "Lkotlin/Function1;",
        "closeCondition",
        "getInitialState",
        "additionalFields",
        "",
        "newStateCallbackFlow",
        "Lcom/stremio/core/runtime/RuntimeEvent$Event$NewState;",
        "ctxAction",
        "Lcom/stremio/core/runtime/msg/ActionCtx$Args;",
        "Lkotlin/reflect/KClass;",
        "ctxAction-0E7RQCE",
        "(Lcom/stremio/core/runtime/msg/ActionCtx$Args;Lkotlin/reflect/KClass;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;",
        "dispatchPlayerAction",
        "Lcom/stremio/core/runtime/msg/ActionPlayer$Args;",
        "dispatchLoadAction",
        "Lcom/stremio/core/runtime/msg/ActionLoad$Args;",
        "dispatchUnloadAction",
        "dispatchCtxAction",
        "dispatchProfilesManagerAction",
        "Lcom/stremio/core/runtime/msg/ActionProfilesManager$Args;",
        "dispatchAction",
        "getState",
        "createNewStateCallback",
        "Lcom/stremio/core/Core$EventListener;",
        "fields",
        "emit",
        "createCoreCallback",
        "continuation",
        "Lkotlin/coroutines/Continuation;",
        "submit",
        "f",
        "Lkotlin/Function0;",
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
.field private final cachePath:Ljava/lang/String;

.field private final dispatchScope:Lkotlinx/coroutines/CoroutineScope;

.field private error:Lcom/stremio/core/runtime/EnvError;

.field private final loadCore:Lkotlinx/coroutines/Deferred;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lkotlinx/coroutines/Deferred<",
            "Lcom/stremio/core/runtime/EnvError;",
            ">;"
        }
    .end annotation
.end field

.field private final metaItemCache:Landroid/util/LruCache;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Landroid/util/LruCache<",
            "Ljava/lang/String;",
            "Lcom/stremio/core/types/resource/MetaItem;",
            ">;"
        }
    .end annotation
.end field

.field private final quickSearchApi:Lcom/stremio/common/api/QuickSearchApi;

.field private final storage:Lcom/stremio/core/Storage;


# direct methods
.method static constructor <clinit>()V
    .registers 0

    return-void
.end method

.method public constructor <init>(Ljava/lang/String;Lcom/stremio/core/Storage;Lcom/stremio/common/api/QuickSearchApi;)V
    .registers 10

    const-string v0, "cachePath"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    const-string v0, "storage"

    invoke-static {p2, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    const-string v0, "quickSearchApi"

    invoke-static {p3, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 96
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 97
    iput-object p1, p0, Lcom/stremio/common/api/StremioApi;->cachePath:Ljava/lang/String;

    .line 98
    iput-object p2, p0, Lcom/stremio/common/api/StremioApi;->storage:Lcom/stremio/core/Storage;

    .line 99
    iput-object p3, p0, Lcom/stremio/common/api/StremioApi;->quickSearchApi:Lcom/stremio/common/api/QuickSearchApi;

    const/4 p1, 0x1

    const/4 p2, 0x0

    .line 102
    invoke-static {p2, p1, p2}, Lkotlinx/coroutines/SupervisorKt;->SupervisorJob$default(Lkotlinx/coroutines/Job;ILjava/lang/Object;)Lkotlinx/coroutines/CompletableJob;

    move-result-object p1

    invoke-static {}, Lkotlinx/coroutines/Dispatchers;->getIO()Lkotlinx/coroutines/CoroutineDispatcher;

    move-result-object p3

    check-cast p3, Lkotlin/coroutines/CoroutineContext;

    invoke-interface {p1, p3}, Lkotlinx/coroutines/CompletableJob;->plus(Lkotlin/coroutines/CoroutineContext;)Lkotlin/coroutines/CoroutineContext;

    move-result-object p1

    invoke-static {p1}, Lkotlinx/coroutines/CoroutineScopeKt;->CoroutineScope(Lkotlin/coroutines/CoroutineContext;)Lkotlinx/coroutines/CoroutineScope;

    move-result-object v0

    iput-object v0, p0, Lcom/stremio/common/api/StremioApi;->dispatchScope:Lkotlinx/coroutines/CoroutineScope;

    .line 103
    new-instance p1, Landroid/util/LruCache;

    const/16 p3, 0x64

    invoke-direct {p1, p3}, Landroid/util/LruCache;-><init>(I)V

    iput-object p1, p0, Lcom/stremio/common/api/StremioApi;->metaItemCache:Landroid/util/LruCache;

    .line 104
    new-instance p1, Lcom/stremio/common/api/StremioApi$loadCore$1;

    invoke-direct {p1, p0, p2}, Lcom/stremio/common/api/StremioApi$loadCore$1;-><init>(Lcom/stremio/common/api/StremioApi;Lkotlin/coroutines/Continuation;)V

    move-object v3, p1

    check-cast v3, Lkotlin/jvm/functions/Function2;

    const/4 v4, 0x3

    const/4 v5, 0x0

    const/4 v1, 0x0

    const/4 v2, 0x0

    invoke-static/range {v0 .. v5}, Lkotlinx/coroutines/BuildersKt;->async$default(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/CoroutineStart;Lkotlin/jvm/functions/Function2;ILjava/lang/Object;)Lkotlinx/coroutines/Deferred;

    move-result-object p1

    iput-object p1, p0, Lcom/stremio/common/api/StremioApi;->loadCore:Lkotlinx/coroutines/Deferred;

    return-void
.end method

.method public static final synthetic access$createCoreCallback(Lcom/stremio/common/api/StremioApi;Lkotlin/reflect/KClass;Lkotlin/coroutines/Continuation;)Lcom/stremio/core/Core$EventListener;
    .registers 3

    .line 96
    invoke-direct {p0, p1, p2}, Lcom/stremio/common/api/StremioApi;->createCoreCallback(Lkotlin/reflect/KClass;Lkotlin/coroutines/Continuation;)Lcom/stremio/core/Core$EventListener;

    move-result-object p0

    return-object p0
.end method

.method public static final synthetic access$createNewStateCallback(Lcom/stremio/common/api/StremioApi;Ljava/util/Set;Lkotlin/jvm/functions/Function1;)Lcom/stremio/core/Core$EventListener;
    .registers 3

    .line 96
    invoke-direct {p0, p1, p2}, Lcom/stremio/common/api/StremioApi;->createNewStateCallback(Ljava/util/Set;Lkotlin/jvm/functions/Function1;)Lcom/stremio/core/Core$EventListener;

    move-result-object p0

    return-object p0
.end method

.method public static final synthetic access$ctxAction-0E7RQCE(Lcom/stremio/common/api/StremioApi;Lcom/stremio/core/runtime/msg/ActionCtx$Args;Lkotlin/reflect/KClass;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .registers 4

    .line 96
    invoke-direct {p0, p1, p2, p3}, Lcom/stremio/common/api/StremioApi;->ctxAction-0E7RQCE(Lcom/stremio/core/runtime/msg/ActionCtx$Args;Lkotlin/reflect/KClass;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public static final synthetic access$dispatchAction(Lcom/stremio/common/api/StremioApi;Lcom/stremio/core/runtime/msg/Action$Type;Lcom/stremio/core/Field;)V
    .registers 3

    .line 96
    invoke-direct {p0, p1, p2}, Lcom/stremio/common/api/StremioApi;->dispatchAction(Lcom/stremio/core/runtime/msg/Action$Type;Lcom/stremio/core/Field;)V

    return-void
.end method

.method public static final synthetic access$dispatchLoadAction(Lcom/stremio/common/api/StremioApi;Lcom/stremio/core/runtime/msg/ActionLoad$Args;Lcom/stremio/core/Field;)V
    .registers 3

    .line 96
    invoke-direct {p0, p1, p2}, Lcom/stremio/common/api/StremioApi;->dispatchLoadAction(Lcom/stremio/core/runtime/msg/ActionLoad$Args;Lcom/stremio/core/Field;)V

    return-void
.end method

.method public static final synthetic access$dispatchPlayerAction(Lcom/stremio/common/api/StremioApi;Lcom/stremio/core/runtime/msg/ActionPlayer$Args;)V
    .registers 2

    .line 96
    invoke-direct {p0, p1}, Lcom/stremio/common/api/StremioApi;->dispatchPlayerAction(Lcom/stremio/core/runtime/msg/ActionPlayer$Args;)V

    return-void
.end method

.method public static final synthetic access$getCachePath$p(Lcom/stremio/common/api/StremioApi;)Ljava/lang/String;
    .registers 1

    .line 96
    iget-object p0, p0, Lcom/stremio/common/api/StremioApi;->cachePath:Ljava/lang/String;

    return-object p0
.end method

.method public static final synthetic access$getError$p(Lcom/stremio/common/api/StremioApi;)Lcom/stremio/core/runtime/EnvError;
    .registers 1

    .line 96
    iget-object p0, p0, Lcom/stremio/common/api/StremioApi;->error:Lcom/stremio/core/runtime/EnvError;

    return-object p0
.end method

.method public static final synthetic access$getMetaItemCache$p(Lcom/stremio/common/api/StremioApi;)Landroid/util/LruCache;
    .registers 1

    .line 96
    iget-object p0, p0, Lcom/stremio/common/api/StremioApi;->metaItemCache:Landroid/util/LruCache;

    return-object p0
.end method

.method public static final synthetic access$getStorage$p(Lcom/stremio/common/api/StremioApi;)Lcom/stremio/core/Storage;
    .registers 1

    .line 96
    iget-object p0, p0, Lcom/stremio/common/api/StremioApi;->storage:Lcom/stremio/core/Storage;

    return-object p0
.end method

.method public static final synthetic access$newStateCallbackFlow(Lcom/stremio/common/api/StremioApi;Lcom/stremio/core/Field;ZLjava/util/Set;)Lkotlinx/coroutines/flow/Flow;
    .registers 4

    .line 96
    invoke-direct {p0, p1, p2, p3}, Lcom/stremio/common/api/StremioApi;->newStateCallbackFlow(Lcom/stremio/core/Field;ZLjava/util/Set;)Lkotlinx/coroutines/flow/Flow;

    move-result-object p0

    return-object p0
.end method

.method public static final synthetic access$setError$p(Lcom/stremio/common/api/StremioApi;Lcom/stremio/core/runtime/EnvError;)V
    .registers 2

    .line 96
    iput-object p1, p0, Lcom/stremio/common/api/StremioApi;->error:Lcom/stremio/core/runtime/EnvError;

    return-void
.end method

.method public static final synthetic access$startAnalyticsPublishing(Lcom/stremio/common/api/StremioApi;)V
    .registers 1

    .line 96
    invoke-direct {p0}, Lcom/stremio/common/api/StremioApi;->startAnalyticsPublishing()V

    return-void
.end method

.method public static final synthetic access$submit(Lcom/stremio/common/api/StremioApi;Lkotlin/jvm/functions/Function0;)V
    .registers 2

    .line 96
    invoke-direct {p0, p1}, Lcom/stremio/common/api/StremioApi;->submit(Lkotlin/jvm/functions/Function0;)V

    return-void
.end method

.method private final authLinkAction(Lcom/stremio/core/runtime/msg/Action$Type;)Lkotlinx/coroutines/flow/Flow;
    .registers 5
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lcom/stremio/core/runtime/msg/Action$Type<",
            "*>;)",
            "Lkotlinx/coroutines/flow/Flow<",
            "Lcom/stremio/core/models/AuthLink;",
            ">;"
        }
    .end annotation

    .line 855
    sget-object v0, Lcom/stremio/core/Field$AUTH_LINK;->INSTANCE:Lcom/stremio/core/Field$AUTH_LINK;

    check-cast v0, Lcom/stremio/core/Field;

    const/4 v1, 0x1

    .line 1393
    invoke-static {}, Lkotlin/collections/SetsKt;->emptySet()Ljava/util/Set;

    move-result-object v2

    .line 1395
    invoke-static {p0, v0, v1, v2}, Lcom/stremio/common/api/StremioApi;->access$newStateCallbackFlow(Lcom/stremio/common/api/StremioApi;Lcom/stremio/core/Field;ZLjava/util/Set;)Lkotlinx/coroutines/flow/Flow;

    move-result-object v1

    .line 1396
    invoke-static {v1}, Lkotlinx/coroutines/flow/FlowKt;->conflate(Lkotlinx/coroutines/flow/Flow;)Lkotlinx/coroutines/flow/Flow;

    move-result-object v1

    .line 1400
    new-instance v2, Lcom/stremio/common/api/StremioApi$authLinkAction$$inlined$fieldStateFlow$default$1;

    invoke-direct {v2, v1, p0, v0}, Lcom/stremio/common/api/StremioApi$authLinkAction$$inlined$fieldStateFlow$default$1;-><init>(Lkotlinx/coroutines/flow/Flow;Lcom/stremio/common/api/StremioApi;Lcom/stremio/core/Field;)V

    check-cast v2, Lkotlinx/coroutines/flow/Flow;

    new-instance v0, Lcom/stremio/common/api/StremioApi$authLinkAction$$inlined$fieldStateFlow$default$2;

    invoke-direct {v0, v2}, Lcom/stremio/common/api/StremioApi$authLinkAction$$inlined$fieldStateFlow$default$2;-><init>(Lkotlinx/coroutines/flow/Flow;)V

    check-cast v0, Lkotlinx/coroutines/flow/Flow;

    .line 1407
    new-instance v1, Lcom/stremio/common/api/StremioApi$authLinkAction$$inlined$fieldStateFlow$default$3;

    const/4 v2, 0x0

    invoke-direct {v1, v2}, Lcom/stremio/common/api/StremioApi$authLinkAction$$inlined$fieldStateFlow$default$3;-><init>(Lkotlin/coroutines/Continuation;)V

    check-cast v1, Lkotlin/jvm/functions/Function3;

    invoke-static {v0, v1}, Lkotlinx/coroutines/flow/FlowKt;->transformWhile(Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function3;)Lkotlinx/coroutines/flow/Flow;

    move-result-object v0

    .line 1411
    invoke-static {v0}, Lkotlinx/coroutines/flow/FlowKt;->distinctUntilChanged(Lkotlinx/coroutines/flow/Flow;)Lkotlinx/coroutines/flow/Flow;

    move-result-object v0

    .line 856
    new-instance v1, Lcom/stremio/common/api/StremioApi$authLinkAction$1;

    invoke-direct {v1, p0, p1, v2}, Lcom/stremio/common/api/StremioApi$authLinkAction$1;-><init>(Lcom/stremio/common/api/StremioApi;Lcom/stremio/core/runtime/msg/Action$Type;Lkotlin/coroutines/Continuation;)V

    check-cast v1, Lkotlin/jvm/functions/Function2;

    invoke-static {v0, v1}, Lkotlinx/coroutines/flow/FlowKt;->onStart(Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/flow/Flow;

    move-result-object p0

    return-object p0
.end method

.method private final catalogsWithExtra(Ljava/util/List;Lcom/stremio/core/Field;)Lkotlinx/coroutines/flow/Flow;
    .registers 9
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "Lcom/stremio/core/types/addon/ExtraValue;",
            ">;",
            "Lcom/stremio/core/Field;",
            ")",
            "Lkotlinx/coroutines/flow/Flow<",
            "Lcom/stremio/core/models/CatalogsWithExtra;",
            ">;"
        }
    .end annotation

    .line 819
    new-instance v0, Lcom/stremio/core/models/CatalogsWithExtra$Selected;

    const/4 v4, 0x5

    const/4 v5, 0x0

    const/4 v1, 0x0

    const/4 v3, 0x0

    move-object v2, p1

    invoke-direct/range {v0 .. v5}, Lcom/stremio/core/models/CatalogsWithExtra$Selected;-><init>(Ljava/lang/String;Ljava/util/List;Ljava/util/Map;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    .line 823
    sget-object p1, Lcom/stremio/core/Field$CTX;->INSTANCE:Lcom/stremio/core/Field$CTX;

    invoke-static {p1}, Lkotlin/collections/SetsKt;->setOf(Ljava/lang/Object;)Ljava/util/Set;

    move-result-object p1

    const/4 v1, 0x1

    .line 1275
    invoke-static {p0, p2, v1, p1}, Lcom/stremio/common/api/StremioApi;->access$newStateCallbackFlow(Lcom/stremio/common/api/StremioApi;Lcom/stremio/core/Field;ZLjava/util/Set;)Lkotlinx/coroutines/flow/Flow;

    move-result-object p1

    .line 1276
    invoke-static {p1}, Lkotlinx/coroutines/flow/FlowKt;->conflate(Lkotlinx/coroutines/flow/Flow;)Lkotlinx/coroutines/flow/Flow;

    move-result-object p1

    .line 1280
    new-instance v1, Lcom/stremio/common/api/StremioApi$catalogsWithExtra$$inlined$fieldStateFlow$default$1;

    invoke-direct {v1, p1, p0, p2}, Lcom/stremio/common/api/StremioApi$catalogsWithExtra$$inlined$fieldStateFlow$default$1;-><init>(Lkotlinx/coroutines/flow/Flow;Lcom/stremio/common/api/StremioApi;Lcom/stremio/core/Field;)V

    check-cast v1, Lkotlinx/coroutines/flow/Flow;

    new-instance p1, Lcom/stremio/common/api/StremioApi$catalogsWithExtra$$inlined$fieldStateFlow$default$2;

    invoke-direct {p1, v1, v0}, Lcom/stremio/common/api/StremioApi$catalogsWithExtra$$inlined$fieldStateFlow$default$2;-><init>(Lkotlinx/coroutines/flow/Flow;Lcom/stremio/core/models/CatalogsWithExtra$Selected;)V

    check-cast p1, Lkotlinx/coroutines/flow/Flow;

    .line 1287
    new-instance v1, Lcom/stremio/common/api/StremioApi$catalogsWithExtra$$inlined$fieldStateFlow$default$3;

    const/4 v2, 0x0

    invoke-direct {v1, v2}, Lcom/stremio/common/api/StremioApi$catalogsWithExtra$$inlined$fieldStateFlow$default$3;-><init>(Lkotlin/coroutines/Continuation;)V

    check-cast v1, Lkotlin/jvm/functions/Function3;

    invoke-static {p1, v1}, Lkotlinx/coroutines/flow/FlowKt;->transformWhile(Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function3;)Lkotlinx/coroutines/flow/Flow;

    move-result-object p1

    .line 1291
    invoke-static {p1}, Lkotlinx/coroutines/flow/FlowKt;->distinctUntilChanged(Lkotlinx/coroutines/flow/Flow;)Lkotlinx/coroutines/flow/Flow;

    move-result-object p1

    .line 824
    new-instance v1, Lcom/stremio/common/api/StremioApi$catalogsWithExtra$2;

    invoke-direct {v1, p0, v0, p2, v2}, Lcom/stremio/common/api/StremioApi$catalogsWithExtra$2;-><init>(Lcom/stremio/common/api/StremioApi;Lcom/stremio/core/models/CatalogsWithExtra$Selected;Lcom/stremio/core/Field;Lkotlin/coroutines/Continuation;)V

    check-cast v1, Lkotlin/jvm/functions/Function2;

    invoke-static {p1, v1}, Lkotlinx/coroutines/flow/FlowKt;->onStart(Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/flow/Flow;

    move-result-object p0

    return-object p0
.end method

.method private final createCoreCallback(Lkotlin/reflect/KClass;Lkotlin/coroutines/Continuation;)Lcom/stremio/core/Core$EventListener;
    .registers 3
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "<T:",
            "Lcom/stremio/core/runtime/msg/Event$Type<",
            "*>;>(",
            "Lkotlin/reflect/KClass<",
            "TT;>;",
            "Lkotlin/coroutines/Continuation<",
            "-",
            "Lkotlin/Result<",
            "+TT;>;>;)",
            "Lcom/stremio/core/Core$EventListener;"
        }
    .end annotation

    .line 948
    new-instance p0, Lcom/stremio/common/api/StremioApi$createCoreCallback$1;

    invoke-direct {p0, p1, p2}, Lcom/stremio/common/api/StremioApi$createCoreCallback$1;-><init>(Lkotlin/reflect/KClass;Lkotlin/coroutines/Continuation;)V

    check-cast p0, Lcom/stremio/core/Core$EventListener;

    return-object p0
.end method

.method private final createNewStateCallback(Ljava/util/Set;Lkotlin/jvm/functions/Function1;)Lcom/stremio/core/Core$EventListener;
    .registers 3
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/Set<",
            "+",
            "Lcom/stremio/core/Field;",
            ">;",
            "Lkotlin/jvm/functions/Function1<",
            "-",
            "Lcom/stremio/core/runtime/RuntimeEvent$Event$NewState;",
            "Lkotlin/Unit;",
            ">;)",
            "Lcom/stremio/core/Core$EventListener;"
        }
    .end annotation

    .line 937
    new-instance p0, Lcom/stremio/common/api/StremioApi$$ExternalSyntheticLambda0;

    invoke-direct {p0, p2, p1}, Lcom/stremio/common/api/StremioApi$$ExternalSyntheticLambda0;-><init>(Lkotlin/jvm/functions/Function1;Ljava/util/Set;)V

    return-object p0
.end method

.method static final createNewStateCallback$lambda$0(Lkotlin/jvm/functions/Function1;Ljava/util/Set;Lcom/stremio/core/runtime/RuntimeEvent;)V
    .registers 5

    const-string v0, "event"

    invoke-static {p2, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V

    .line 938
    invoke-virtual {p2}, Lcom/stremio/core/runtime/RuntimeEvent;->getEvent()Lcom/stremio/core/runtime/RuntimeEvent$Event;

    move-result-object v0

    instance-of v1, v0, Lcom/stremio/core/runtime/RuntimeEvent$Event$NewState;

    if-eqz v1, :cond_10

    check-cast v0, Lcom/stremio/core/runtime/RuntimeEvent$Event$NewState;

    goto :goto_11

    :cond_10
    const/4 v0, 0x0

    :goto_11
    if-eqz v0, :cond_55

    invoke-virtual {v0}, Lcom/stremio/core/runtime/RuntimeEvent$Event$NewState;->getValue()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/stremio/core/runtime/RuntimeEvent$NewState;

    if-eqz v0, :cond_55

    invoke-virtual {v0}, Lcom/stremio/core/runtime/RuntimeEvent$NewState;->getFields()Ljava/util/List;

    move-result-object v0

    if-eqz v0, :cond_55

    check-cast v0, Ljava/lang/Iterable;

    .line 1442
    instance-of v1, v0, Ljava/util/Collection;

    if-eqz v1, :cond_31

    move-object v1, v0

    check-cast v1, Ljava/util/Collection;

    invoke-interface {v1}, Ljava/util/Collection;->isEmpty()Z

    move-result v1

    if-eqz v1, :cond_31

    goto :goto_55

    .line 1443
    :cond_31
    invoke-interface {v0}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :cond_35
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_55

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/stremio/core/Field;

    .line 938
    invoke-interface {p1, v1}, Ljava/util/Set;->contains(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_35

    .line 939
    invoke-virtual {p2}, Lcom/stremio/core/runtime/RuntimeEvent;->getEvent()Lcom/stremio/core/runtime/RuntimeEvent$Event;

    move-result-object p1

    const-string p2, "null cannot be cast to non-null type com.stremio.core.runtime.RuntimeEvent.Event.NewState"

    invoke-static {p1, p2}, Lkotlin/jvm/internal/Intrinsics;->checkNotNull(Ljava/lang/Object;Ljava/lang/String;)V

    check-cast p1, Lcom/stremio/core/runtime/RuntimeEvent$Event$NewState;

    invoke-interface {p0, p1}, Lkotlin/jvm/functions/Function1;->invoke(Ljava/lang/Object;)Ljava/lang/Object;

    :cond_55
    :goto_55
    return-void
.end method

.method private final ctxAction-0E7RQCE(Lcom/stremio/core/runtime/msg/ActionCtx$Args;Lkotlin/reflect/KClass;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .registers 8
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "<T:",
            "Lcom/stremio/core/runtime/msg/Event$Type<",
            "*>;>(",
            "Lcom/stremio/core/runtime/msg/ActionCtx$Args<",
            "*>;",
            "Lkotlin/reflect/KClass<",
            "TT;>;",
            "Lkotlin/coroutines/Continuation<",
            "-",
            "Lkotlin/Result<",
            "+TT;>;>;)",
            "Ljava/lang/Object;"
        }
    .end annotation

    instance-of v0, p3, Lcom/stremio/common/api/StremioApi$ctxAction$1;

    if-eqz v0, :cond_14

    move-object v0, p3

    check-cast v0, Lcom/stremio/common/api/StremioApi$ctxAction$1;

    iget v1, v0, Lcom/stremio/common/api/StremioApi$ctxAction$1;->label:I

    const/high16 v2, -0x80000000

    and-int/2addr v1, v2

    if-eqz v1, :cond_14

    iget p3, v0, Lcom/stremio/common/api/StremioApi$ctxAction$1;->label:I

    sub-int/2addr p3, v2

    iput p3, v0, Lcom/stremio/common/api/StremioApi$ctxAction$1;->label:I

    goto :goto_19


... [truncated: exceeds 60000 bytes, total 529555 bytes]