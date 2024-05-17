package com.example.exsercisesecssionfiveapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeScreen()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HomeScreen()
}

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.DarkGray)
    ) {
        Title()
        Row(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .background(Color.Black)
        ) {}
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            InformationLocation()
        }

        TextContent(
            text = "Vui lòng chọn một trong nhưng phương thức sau: ",
            modifier = Modifier
                .width(390.dp)
                .padding(16.dp, 0.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Column(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.weight(9f)
            ) {
                ListPayment()

            }
            Column(
                modifier = Modifier.weight(1f), verticalArrangement = Arrangement.Bottom
            ) {
                Button(
                    onClick = { },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFE734C)
                    )

                ) {
                    Text(
                        text = "Tiếp theo",
                        fontFamily = FontFamily.Serif,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }


    }
}


@Composable
fun Title() {

    Text(
        text = "Thanh toán",
        color = Color.White,
        fontSize = 20.sp,
        fontFamily = FontFamily.Serif,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)

    )

}

@Composable
fun TextContent(text: String, modifier: Modifier?) {
    if (modifier != null) {
        Text(
            text = text,
            color = Color.White,
            fontFamily = FontFamily.Serif,
            maxLines = 3,
            modifier = modifier
        )
    }
}

@Composable
fun InformationLocation() {
    Text(
        text = "Địa chỉ nhận hàng", color = Color.White, fontFamily = FontFamily.Serif,
    )
    Spacer(modifier = Modifier.height(10.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp, 0.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Image(

            painterResource(id = R.drawable.pin),
            contentDescription = "",
            modifier = Modifier
                .width(20.dp)
                .height(20.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp, 0.dp)
        ) {
            TextContent(text = "Trí | 098765432", modifier = Modifier.width(195.dp))
            TextContent(
                text = "22/333 đường Trung Mỹ Tây 1 phường Tân Thới Nhất quận 12, Thành phố Hồ Chí Minh",
                modifier = Modifier.width(195.dp)
            )


        }

    }

}

@Composable
fun Item(
    model: PaymentDTO,
    selectedPayment: PaymentDTO?,
    onPaymentSelected: (PaymentDTO) -> Unit
) {



    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .background(
                Color(model.color), shape = RoundedCornerShape(8.dp)
            )
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Row(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painterResource(id = model.image),
                contentDescription = "",
                modifier = Modifier.size(45.dp, 45.dp),
                contentScale = ContentScale.Fit
            )
            Text(
                text = model.name,
                fontFamily = FontFamily.Serif,
                color = Color.White,
                textAlign = TextAlign.Left,
                modifier = Modifier.padding(start = 40.dp)
            )
        }
        RadioButton(
            selected = (selectedPayment == model), onClick = {
                onPaymentSelected(model)
            }, colors = RadioButtonDefaults.colors(
                selectedColor = Color.Black, unselectedColor = Color.Black
            )

        )


    }
}

class PaymentDTO(var name: String, var image: Int, var color: Long)

@Composable
fun ListPayment() {
    var list = mutableListOf<PaymentDTO>()
    list.add(PaymentDTO("PayPal", R.drawable.paypal, 0xFFFA8500))
    list.add(PaymentDTO("Visa", R.drawable.visa, 0xFFEE8F66))
    list.add(PaymentDTO("Zalo Pay", R.drawable.zalopay, 0xFF00C2FD))
    list.add(PaymentDTO("Momo", R.drawable.momo, 0xFFEC1387))
    list.add(PaymentDTO("Thanh toán trực tiếp", R.drawable.payment, 0xFF18EBEA))

    var selectedPayment by remember {
        mutableStateOf<PaymentDTO?>(null)
    }

    LazyColumn(
    ) {
        items(list) { item ->
            Item(model = item, selectedPayment = selectedPayment, onPaymentSelected = {
                selectedPayment = it
            })
        }
    }
}