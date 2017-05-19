package com.litho;

import android.graphics.Color;
import android.net.Uri;

import com.example.admin.myapplication.R;
import com.facebook.litho.Column;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.annotations.ResType;
import com.facebook.litho.fresco.FrescoImage;
import com.facebook.litho.widget.Text;
import com.facebook.yoga.YogaAlign;
import com.facebook.yoga.YogaDirection;
import com.facebook.yoga.YogaEdge;

/**
 * Created by maraonda admin on 2017/4/20.
 */
@LayoutSpec
public class HeaderComponentSpec {
    @OnCreateLayout
    static ComponentLayout onCreateLayout(
            ComponentContext c,
            @Prop Uri imageUri,
            @Prop(resType = ResType.STRING) String title,
            @Prop(resType = ResType.STRING, optional = true) String subtitle){

        return Column.create(c)
                .layoutDirection(YogaDirection.RTL)
                .alignItems(YogaAlign.CENTER)
                .backgroundRes(R.mipmap.xsmarkets01)
                .paddingDip(YogaEdge.ALL,10)
                .child(
//                        FrescoImage.create(c)
//                                .progressBarImageRes(R.mipmap.ic_launcher)
//                                .withLayout()
//                                .widthRes(R.dimen.fab_margin)
//                                .heightRes(R.dimen.fab_margin)
//                                .marginDip(YogaEdge.END,16)
                        Text.create(c)
                                .text(title)
                                .textColor(Color.DKGRAY)
                                .textSizeDip(16)
                                .textColorRes(R.color.colorAccent)
                ).child(
                        Column.create(c)
                        .layoutDirection(YogaDirection.INHERIT)
                        .flexGrow(1)
                        .child(
                                Text.create(c)
                                        .text(title)
                                        .textColor(Color.DKGRAY)
                                        .textSizeDip(16)
                                        .textColorRes(R.color.colorAccent))
                        .child(
                                Text.create(c)
                                        .text(subtitle)
                                        .textSizeDip(18)
                                        .withLayout()
                                        .marginRes(YogaEdge.TOP, R.dimen.fab_margin))


                ).build();
    }
}
