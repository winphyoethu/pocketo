package com.winphyoethu.pocketo.screen.setup

import android.widget.Toast
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.winphyoethu.pocketo.R
import com.winphyoethu.pocketo.designsystem.basiccomponent.PocketoButton
import com.winphyoethu.pocketo.designsystem.basiccomponent.PocketoSubTitle
import com.winphyoethu.pocketo.designsystem.basiccomponent.PocketoTitle
import com.winphyoethu.pocketo.designsystem.basiccomponent.PocketoXLTitle
import com.winphyoethu.pocketo.designsystem.icon.PocketoIcons
import com.winphyoethu.pocketo.designsystem.pocketocomponent.PocketoCurrency
import com.winphyoethu.pocketo.designsystem.ui.theme.PocketoTheme
import com.winphyoethu.pocketo.designsystem.ui.theme.largeDp
import com.winphyoethu.pocketo.designsystem.ui.theme.mediumDp
import com.winphyoethu.pocketo.designsystem.ui.theme.smallDp
import com.winphyoethu.pocketo.designsystem.ui.theme.xxLargeDp
import com.winphyoethu.pocketo.designsystem.ui.theme.xxxLargeDp
import com.winphyoethu.pocketo.model.currency.Currency
import kotlinx.coroutines.flow.collectLatest

@Composable
internal fun SetUpRoute(
    onAccountSetUpFinish: () -> Unit,
    viewmodel: SetUpViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val setUpUiState by viewmodel.setUpUiState.collectAsStateWithLifecycle()

    LaunchedEffect(viewmodel.navigationEvent) {
        viewmodel.navigationEvent.collectLatest {
            onAccountSetUpFinish()
        }
    }

    LaunchedEffect(viewmodel.errorEvent) {
        viewmodel.errorEvent.collectLatest {
            Toast.makeText(context, context.getString(it), Toast.LENGTH_SHORT).show()
        }
    }

    SetUp(
        setUpFullName = viewmodel::setFullName,
        setUpProfession = viewmodel::setProfession,
        setUpIncome = viewmodel::setIncome,
        setUpCurrency = viewmodel::setCurrency,
        changeState = viewmodel::changeState,
        showCurrencyBottomSheet = viewmodel::showCurrenciesBottomSheet,
        createAccount = viewmodel::createAccount,
        setUpUiState = setUpUiState
    )
}

@Composable
internal fun SetUp(
    setUpFullName: (String) -> Unit,
    setUpProfession: (String) -> Unit,
    setUpIncome: (String) -> Unit,
    setUpCurrency: (Currency?) -> Unit,
    changeState: (Boolean) -> Unit,
    showCurrencyBottomSheet: () -> Unit,
    createAccount: () -> Unit,
    setUpUiState: SetUpUiState = SetUpUiState()
) {

    val colorAnimation = rememberInfiniteTransition("InfiniteBackground")
    val background by colorAnimation.animateColor(
        MaterialTheme.colorScheme.primary,
        MaterialTheme.colorScheme.secondaryContainer,
        animationSpec = infiniteRepeatable(
            tween(2000), RepeatMode.Reverse
        ), label = stringResource(R.string.set_up_animation)
    )

    val progressAnimation by animateDpAsState(
        if (setUpUiState.state is FinalStep) 100.dp else 0.dp,
        animationSpec = spring(
            stiffness = Spring.StiffnessMediumLow,
            dampingRatio = Spring.DampingRatioLowBouncy
        ), label = stringResource(R.string.set_up_progress_animation)
    )

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            painter = painterResource(R.drawable.bg),
            contentDescription = stringResource(R.string.set_up_backdrop_content_description)
        )
        Box(
            modifier = Modifier
                .width(progressAnimation)
                .background(MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(mediumDp))
                .height(mediumDp)
        )

        if (setUpUiState.showCurrencyBottomSheet) {
            setUpUiState.currencyList?.let { currencyList ->
                CategoryBottomSheet(
                    currencyList,
                    onDismiss = {
                        setUpCurrency(null)
                    }) {
                    setUpCurrency(it)
                }
            }
        }

        Box(contentAlignment = Alignment.TopCenter, modifier = Modifier
            .fillMaxSize()
            .graphicsLayer {
                this.translationY = 180.dp.toPx()
                this.shadowElevation = 0.3f
            }
            .background(color = Color.White, shape = RoundedCornerShape(xxLargeDp))) {

            Column(
                modifier = Modifier.padding(top = xxxLargeDp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(mediumDp)
            ) {
                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .border(smallDp, color = background, shape = CircleShape)
                ) {
                    PocketoXLTitle(
                        title = if (setUpUiState.fullName.isNotEmpty()) setUpUiState.fullName[0].toString() else "",
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(100.dp)
                            .background(color = Color.LightGray, shape = CircleShape)
                            .wrapContentHeight(align = Alignment.CenterVertically)
                            .padding(largeDp)
                    )
                }
                PocketoTitle(title = stringResource(R.string.set_up_welcome))

                AnimatedContent(
                    setUpUiState.state,
                    label = stringResource(R.string.set_up_next_step_animation)
                ) { state ->
                    when (state) {
                        FirstStep -> {
                            Column(
                                horizontalAlignment = Alignment.Start,
                                verticalArrangement = Arrangement.spacedBy(mediumDp)
                            ) {
                                PocketoSubTitle(subtitle = stringResource(R.string.set_up_full_name))
                                TextField(value = setUpUiState.fullName, onValueChange = {
                                    setUpFullName(it)
                                }, placeholder = {
                                    PocketoSubTitle(
                                        subtitle = stringResource(R.string.set_up_full_name),
                                        color = Color.LightGray
                                    )
                                }, colors = TextFieldDefaults.colors().copy(
                                    disabledTextColor = Color.Transparent,
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent,
                                    disabledIndicatorColor = Color.Transparent
                                ), shape = RoundedCornerShape(largeDp)
                                )

                                PocketoSubTitle(subtitle = stringResource(R.string.set_up_profession))
                                TextField(
                                    value = setUpUiState.profession,
                                    onValueChange = {
                                        setUpProfession(it)
                                    },
                                    placeholder = {
                                        PocketoSubTitle(
                                            subtitle = stringResource(R.string.set_up_profession),
                                            color = Color.LightGray
                                        )
                                    },
                                    colors = TextFieldDefaults.colors().copy(
                                        disabledTextColor = Color.Transparent,
                                        focusedIndicatorColor = Color.Transparent,
                                        unfocusedIndicatorColor = Color.Transparent,
                                        disabledIndicatorColor = Color.Transparent
                                    ),
                                    shape = RoundedCornerShape(largeDp)
                                )

                                PocketoButton(
                                    text = stringResource(R.string.set_up_next_step),
                                    isEnabled = setUpUiState.fullName.isNotEmpty()
                                ) {
                                    changeState(false)
                                }
                            }
                        }

                        else -> {
                            Column(
                                horizontalAlignment = Alignment.Start,
                                verticalArrangement = Arrangement.spacedBy(mediumDp)
                            ) {
                                PocketoSubTitle(subtitle = stringResource(R.string.set_up_income))
                                TextField(
                                    value = setUpUiState.income, onValueChange = {
                                        setUpIncome(it)
                                    }, placeholder = {
                                        PocketoSubTitle(
                                            subtitle = stringResource(R.string.set_up_income),
                                            color = Color.LightGray
                                        )
                                    },
                                    colors = TextFieldDefaults.colors().copy(
                                        disabledTextColor = Color.Transparent,
                                        focusedIndicatorColor = Color.Transparent,
                                        unfocusedIndicatorColor = Color.Transparent,
                                        disabledIndicatorColor = Color.Transparent
                                    ),
                                    shape = RoundedCornerShape(largeDp)
                                )

                                PocketoSubTitle(subtitle = stringResource(R.string.set_up_currency))
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(mediumDp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    PocketoSubTitle(
                                        subtitle = "${setUpUiState.currency?.name} (${setUpUiState.currency?.code})",
                                        modifier = Modifier
                                            .background(
                                                MaterialTheme.colorScheme.surfaceContainerHighest,
                                                shape = RoundedCornerShape(largeDp)
                                            )
                                            .padding(largeDp)
                                    )
                                    IconButton(
                                        onClick = {
                                            showCurrencyBottomSheet()
                                        }, modifier = Modifier.background(
                                            color = Color.LightGray,
                                            shape = RoundedCornerShape(largeDp)
                                        )
                                    ) {
                                        Icon(
                                            imageVector = PocketoIcons.Dropdown,
                                            contentDescription = stringResource(R.string.set_up_dropdown_currency_content_description)
                                        )
                                    }
                                }

                                Row(horizontalArrangement = Arrangement.spacedBy(mediumDp)) {
                                    PocketoButton(text = stringResource(R.string.set_up_prev_step)) {
                                        changeState(true)
                                    }
                                    PocketoButton(
                                        text = stringResource(R.string.set_up_finish),
                                        isEnabled = setUpUiState.income.isNotEmpty() && setUpUiState.currency != null
                                    ) {
                                        createAccount()
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryBottomSheet(
    currencyList: List<Currency>,
    onDismiss: () -> Unit,
    onCategoryChosen: (Currency) -> Unit
) {
    ModalBottomSheet(onDismissRequest = {
        onDismiss()
    }) {
        LazyColumn(
            modifier = Modifier.padding(start = largeDp, end = largeDp),
            verticalArrangement = Arrangement.spacedBy(mediumDp)
        ) {
            items(count = currencyList.size, key = { currencyList[it].id }) {
                PocketoCurrency(currency = currencyList[it]) { currency ->
                    onCategoryChosen(currency)
                }
            }
        }
    }
}


@Preview
@Composable
fun SetUpPreview() {
    PocketoTheme(dynamicColor = false) {
        Surface {
            SetUp(
                setUpFullName = {},
                setUpProfession = {},
                setUpIncome = {},
                setUpCurrency = {},
                changeState = {},
                showCurrencyBottomSheet = {},
                createAccount = {}
            )
        }
    }
}