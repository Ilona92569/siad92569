package com.example.feature.ui.exchange

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.feature.ui.copmose.ItemExchange
import com.example.feature.ui.copmose.values.Green
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExchangeScreen(
    viewModel: ExchangeViewModel
) {
    var mExpanded by remember { mutableStateOf(false) }

    val cities = listOf("Брест", "Витебск", "Гомель", "Гродно", "Минск", "Могилёв")

    val selectedCity by viewModel.selectedCity.observeAsState()
    val coroutineScope = rememberCoroutineScope()


    LaunchedEffect(Unit) {
        viewModel.getAllTickets(selectedCity!!)
    }

    val listExchange by viewModel.listCityData.observeAsState()

    val isLoading by viewModel.isLoading.observeAsState()
    val isError by viewModel.isError.observeAsState()

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {

        val (textChoice, textFiledChoice, listLazy, progressBar, textError) = createRefs()


        Text(
            "Выберите город",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Green,
            modifier = Modifier.constrainAs(textChoice) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                bottom.linkTo(textFiledChoice.bottom)
            }
        )

        Column(modifier = Modifier
            .constrainAs(textFiledChoice) {
                top.linkTo(parent.top)
                start.linkTo(textChoice.end, 10.dp)
                end.linkTo(parent.end)
            }
            .width(200.dp)
            .height(50.dp)) {
            ExposedDropdownMenuBox(
                expanded = mExpanded,
                onExpandedChange = { mExpanded = !mExpanded }
            ) {
                TextField(
                    modifier = Modifier.menuAnchor(),
                    value = selectedCity!!,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = mExpanded) }
                )

                ExposedDropdownMenu(expanded = mExpanded,
                    onDismissRequest = { mExpanded = false }) {
                    cities.forEachIndexed { index, text ->
                        DropdownMenuItem(
                            text = { Text(text = text) },
                            onClick = {
                                viewModel.exchangeCity(cities[index])
                                coroutineScope.launch {
                                    viewModel.getAllTickets(selectedCity!!)
                                }
                                mExpanded = false
                            },
                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                        )

                    }
                }

            }
        }
        if (isLoading == true) {
            CircularProgressIndicator(
                color = Color.Gray,
                trackColor = Green,
                modifier = Modifier
                    .size(60.dp)
                    .constrainAs(progressBar) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
            )
        } else {
            if (listExchange.isNullOrEmpty()) {
                Text(
                    viewModel.error.value.toString(),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Green,
                    modifier = Modifier.constrainAs(textError) {
                        top.linkTo(textFiledChoice.bottom, 20.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                )
            } else {
                LazyColumn(modifier = Modifier.constrainAs(listLazy) {
                    top.linkTo(textFiledChoice.bottom, 10.dp)
                    start.linkTo(parent.start)
                }) {
                    itemsIndexed(listExchange!!) { _, item ->
                        Column(
                            verticalArrangement = Arrangement.spacedBy(20.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            ItemExchange(item)
                        }
                    }
                }
            }
        }
    }
}
