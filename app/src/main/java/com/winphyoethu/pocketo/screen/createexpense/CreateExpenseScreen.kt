package com.winphyoethu.pocketo.screen.createexpense

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.winphyoethu.pocketo.designsystem.basiccomponent.PocketoSubTitle
import com.winphyoethu.pocketo.designsystem.pocketocomponent.NumpadButton
import com.winphyoethu.pocketo.designsystem.icon.PocketoIcons
import com.winphyoethu.pocketo.designsystem.pocketocomponent.PocketoAppbar
import com.winphyoethu.pocketo.designsystem.pocketocomponent.PocketoBalanceCard
import com.winphyoethu.pocketo.designsystem.pocketocomponent.PocketoCategory
import com.winphyoethu.pocketo.designsystem.ui.theme.PocketoTheme
import com.winphyoethu.pocketo.designsystem.ui.theme.largeDp
import com.winphyoethu.pocketo.designsystem.ui.theme.mediumDp
import com.winphyoethu.pocketo.model.category.Category
import kotlinx.coroutines.flow.collectLatest

@Composable
fun CreateExpenseRoute(
    onBackClick: () -> Unit,
    viewmodel: CreateExpenseViewModel = hiltViewModel(),
) {
    val createExpenseUiState by viewmodel.createExpenseUiState.collectAsStateWithLifecycle()

    val context = LocalContext.current

    LaunchedEffect(viewmodel.shouldShowStatusMessage) {
        viewmodel.shouldShowStatusMessage.collectLatest {
            Toast.makeText(context, context.getString(it), Toast.LENGTH_SHORT).show()
        }
    }
    CreateExpense(
        onBackClick,
        createExpenseUiState,
        showCategoryBottomSheet = viewmodel::showCategoryBottomSheet,
        observeDescription = viewmodel::listenDescription,
        observeNumpad = viewmodel::listenNumpad,
        observeBackspace = viewmodel::listenNumpad,
        observeDone = viewmodel::createExpense,
        observeCategorySelect = viewmodel::setOrDismissCategory
    )

}

@Composable
private fun CreateExpense(
    onBackClick: () -> Unit,
    createExpenseUiState: CreateExpenseUiState,
    showCategoryBottomSheet: () -> Unit,
    observeDescription: (String) -> Unit,
    observeNumpad: (String) -> Unit,
    observeBackspace: (String) -> Unit,
    observeDone: () -> Unit,
    observeCategorySelect: (Category?) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(16.dp)
    ) {
        PocketoAppbar(
            icon = PocketoIcons.Back,
            iconDescription = "Back Button",
            title = "Add Amount",
            iconAction = { onBackClick() }
        )

        Spacer(modifier = Modifier.size(24.dp))

        PocketoBalanceCard(createExpenseUiState.amount, "AUD", "$")

        Spacer(modifier = Modifier.size(16.dp))
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(mediumDp)
            ) {
                PocketoSubTitle(subtitle = "Expenses made for", color = Color.Gray)
                PocketoSubTitle(subtitle = createExpenseUiState.category?.name ?: "")
            }
            IconButton(
                onClick = {
                    showCategoryBottomSheet()
                }, modifier = Modifier.background(
                    color = Color.LightGray, shape = RoundedCornerShape(largeDp)
                )
            ) {
                Icon(
                    imageVector = PocketoIcons.Dropdown,
                    contentDescription = "dropdown"
                )
            }
        }
        Spacer(modifier = Modifier.size(largeDp))
        PocketoSubTitle(subtitle = "Description", color = Color.Gray)
        Spacer(modifier = Modifier.height(mediumDp))
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Transparent),
            shape = RoundedCornerShape(16.dp),
            colors = TextFieldDefaults.colors().copy(
                disabledTextColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            maxLines = 2,
            value = createExpenseUiState.description,
            onValueChange = { value ->
                observeDescription(value)
            },
            placeholder = {
                Text("Description", color = Color.LightGray)
            })

        Spacer(modifier = Modifier.height(mediumDp))

        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
            NumpadButton("1") { observeNumpad("1") }
            NumpadButton("2") { observeNumpad("2") }
            NumpadButton("3") { observeNumpad("3") }
        }
        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
            NumpadButton("4") { observeNumpad("4") }
            NumpadButton("5") { observeNumpad("5") }
            NumpadButton("6") { observeNumpad("6") }
        }
        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
            NumpadButton("7") { observeNumpad("7") }
            NumpadButton("8") { observeNumpad("8") }
            NumpadButton("9") { observeNumpad("9") }
        }
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                colors = ButtonDefaults.buttonColors()
                    .copy(containerColor = Color.Transparent, contentColor = Color.Black),
                onClick = {
                    if (createExpenseUiState.amount != "0.0")
                        observeBackspace("")
                },
                modifier = Modifier
                    .size(100.dp)
                    .padding(16.dp)
            ) {
                Icon(
                    imageVector = PocketoIcons.Backspace,
                    contentDescription = "backspace",
                    modifier = Modifier.fillMaxSize()
                )
            }
            NumpadButton("0") {
                observeNumpad("0")
            }
            Button(
                onClick = { observeDone() },
                modifier = Modifier
                    .size(100.dp)
                    .padding(16.dp)
            ) {
                Icon(
                    imageVector = PocketoIcons.Done,
                    contentDescription = "Done",
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

        if (createExpenseUiState.state is ShowCategoryBottomSheet) {
            CategoryBottomSheet(
                createExpenseUiState.state.categoryList,
                onDismiss = { observeCategorySelect(null) }
            ) { category ->
                observeCategorySelect(category)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryBottomSheet(
    categoryList: List<Category>,
    onDismiss: () -> Unit,
    onCategoryChosen: (Category) -> Unit
) {
    ModalBottomSheet(onDismissRequest = {
        onDismiss()
    }) {
        LazyColumn(
            modifier = Modifier.padding(start = largeDp, end = largeDp),
            verticalArrangement = Arrangement.spacedBy(mediumDp)
        ) {
            items(count = categoryList.size, key = { categoryList[it].id }) {
                PocketoCategory(category = categoryList[it]) { category ->
                    onCategoryChosen(category)
                }
            }
        }
    }
}

@Preview
@Composable
fun CreateExpensePreview() {
    PocketoTheme(dynamicColor = false) {
        CreateExpense(
            onBackClick = { },
            CreateExpenseUiState(Initial),
            showCategoryBottomSheet = { },
            observeDescription = { },
            observeNumpad = { },
            observeBackspace = { },
            observeDone = { },
            observeCategorySelect = { }
        )
    }
}