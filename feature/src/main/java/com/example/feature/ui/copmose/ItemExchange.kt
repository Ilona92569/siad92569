package com.example.feature.ui.copmose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.data.model.CityData
import com.example.feature.R
import com.example.feature.ui.copmose.values.GreenBack

@Composable
@Preview
fun ItemExchange(
    cityData: CityData = CityData()
) {
    Column(modifier = Modifier.padding(top = 10.dp)) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(30.dp))
                .background(GreenBack)
                .padding(20.dp)
        ) {
            val (textStreet, textCurrency, textPass, textBuy,
                imageUsd, imageEur, imageRub,
                textUsd, textEur, textRub,
                usdPass, usdBuy,
                eurPass, eurBuy,
                rubPass, rubBuy) = createRefs()

            Text("${cityData.street.toString()} ${cityData.homeNumber.toString()}",
                fontSize = 18.sp,
                modifier = Modifier.constrainAs(textStreet) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                })

            Text(
                "Валюта",
                fontSize = 14.sp,
                modifier = Modifier.constrainAs(textCurrency) {
                    top.linkTo(textStreet.bottom, 10.dp)
                    start.linkTo(parent.start)
                },
                color = Color.Gray
            )

            Text(
                "Продать",
                fontSize = 14.sp,
                modifier = Modifier.constrainAs(textPass) {
                    top.linkTo(textStreet.bottom, 10.dp)
                    start.linkTo(textCurrency.end)
                    end.linkTo(textBuy.start)
                },
                color = Color.Gray
            )

            Text(
                "Купить",
                fontSize = 14.sp,
                modifier = Modifier.constrainAs(textBuy) {
                    top.linkTo(textStreet.bottom, 10.dp)
                    start.linkTo(textPass.end)
                    end.linkTo(parent.end)
                },
                color = Color.Gray
            )

            Image(painter = painterResource(R.drawable.usd),
                contentDescription = "usd",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(30.dp)
                    .constrainAs(imageUsd) {
                        top.linkTo(textCurrency.bottom, 10.dp)
                        start.linkTo(textCurrency.start)
                    }
                    .clip(CircleShape)
            )

            Text(
                "USD",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .constrainAs(textUsd) {
                        top.linkTo(imageUsd.top)
                        start.linkTo(imageUsd.end, 10.dp)
                        bottom.linkTo(imageUsd.bottom)
                    },
                color = Color.Gray
            )

            Text(
                cityData.USDIn.toString(),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .constrainAs(usdPass) {
                        top.linkTo(imageUsd.top)
                        start.linkTo(textPass.start)
                        bottom.linkTo(imageUsd.bottom)
                    },
                color = Color.Gray
            )

            Text(
                cityData.USDOut.toString(),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .constrainAs(usdBuy) {
                        top.linkTo(imageUsd.top)
                        start.linkTo(textBuy.start)
                        bottom.linkTo(imageUsd.bottom)
                    },
                color = Color.Gray
            )

            Image(painter = painterResource(R.drawable.eur),
                contentDescription = "usd",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(30.dp)
                    .constrainAs(imageEur) {
                        top.linkTo(imageUsd.bottom, 10.dp)
                        start.linkTo(textCurrency.start)
                    }
                    .clip(CircleShape)
            )

            Text(
                "EUR",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .constrainAs(textEur) {
                        top.linkTo(imageEur.top)
                        start.linkTo(imageEur.end, 10.dp)
                        bottom.linkTo(imageEur.bottom)
                    },
                color = Color.Gray
            )

            Text(
                cityData.EURIn.toString(),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .constrainAs(eurPass) {
                        top.linkTo(imageEur.top)
                        start.linkTo(textPass.start)
                        bottom.linkTo(imageEur.bottom)
                    },
                color = Color.Gray
            )

            Text(
                cityData.EUROut.toString(),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .constrainAs(eurBuy) {
                        top.linkTo(imageEur.top)
                        start.linkTo(textBuy.start)
                        bottom.linkTo(imageEur.bottom)
                    },
                color = Color.Gray
            )

            Image(painter = painterResource(R.drawable.rub),
                contentDescription = "usd",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(30.dp)
                    .constrainAs(imageRub) {
                        top.linkTo(imageEur.bottom, 10.dp)
                        start.linkTo(textCurrency.start)
                    }
                    .clip(CircleShape)
            )

            Text(
                "RUB",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .constrainAs(textRub) {
                        top.linkTo(imageRub.top)
                        start.linkTo(imageRub.end, 10.dp)
                        bottom.linkTo(imageRub.bottom)
                    },
                color = Color.Gray
            )

            Text(
                cityData.RUBIn.toString(),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .constrainAs(rubPass) {
                        top.linkTo(imageRub.top)
                        start.linkTo(textPass.start)
                        bottom.linkTo(imageRub.bottom)
                    },
                color = Color.Gray
            )

            Text(
                cityData.RUBOut.toString(),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .constrainAs(rubBuy) {
                        top.linkTo(imageRub.top)
                        start.linkTo(textBuy.start)
                        bottom.linkTo(imageRub.bottom)
                    },
                color = Color.Gray
            )
        }
    }
}



