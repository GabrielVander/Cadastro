package vander.gabriel.cadastro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import vander.gabriel.cadastro.ui.theme.CadastroTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CadastroTheme {
                Scaffold(topBar = {
                    TopAppBar(
                        title = { Text("Cadastro") },
                    )
                }) {
                    Column(
                        Modifier
                            .padding(10.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.Start,
                    ) {
                        TextFieldWithLabel(label = "Full name")
                        Spacer(modifier = Modifier.size(10.dp))
                        TextFieldWithLabel(
                            label = "Phone number",
                            placeholder = { Text("Ex.: 12-93456-7890") },
                        )
                        Spacer(modifier = Modifier.size(10.dp))
                        TextFieldWithLabel(label = "Email")
                        Spacer(modifier = Modifier.size(10.dp))
                        CheckboxWithLabel("Signup for newsletter", onChange = { })
                        Spacer(modifier = Modifier.size(10.dp))
                        GenderField()
                        Spacer(modifier = Modifier.size(10.dp))
                        TextFieldWithLabel(label = "City")
                        Spacer(modifier = Modifier.size(10.dp))
                        Text("State")
                        Spacer(modifier = Modifier.size(10.dp))
                        SateSelection()
                        Spacer(modifier = Modifier.size(10.dp))
                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Button(onClick = { }) {
                                Text("Clear")
                            }
                            Button(onClick = { }) {
                                Text("Save")
                            }
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun DropDownList(
        open: Boolean = false,
        list: List<String>,
        toggleMenuRequest: (Boolean) -> Unit,
        selectedString: (String) -> Unit
    ) {
        DropdownMenu(
            expanded = open,
            onDismissRequest = { toggleMenuRequest(false) },
        ) {
            list.forEach {
                DropdownMenuItem(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        toggleMenuRequest(false)
                        selectedString(it)
                    }
                ) {
                    Text(
                        it, modifier = Modifier
                            .wrapContentWidth()
                    )
                }
            }
        }
    }

    @Composable
    fun SateSelection() {
        val stateList = listOf(
            "Acre",
            "Alagoas",
            "Amapá",
            "Amazonas",
            "Bahia",
            "Ceará",
            "Distrito Federal",
            "Espírito Santo",
            "Goiás",
            "Maranhão",
            "Mato Grosso",
            "Mato Grosso do Sul",
            "Minas Gerais",
            "Pará",
            "Paraíba",
            "Paraná",
            "Pernambuco",
            "Piauí",
            "Rio de Janeiro",
            "Rio Grande do Norte",
            "Rio Grande do Sul",
            "Rondônia",
            "Roraima",
            "Santa Catarina",
            "São Paulo",
            "Sergipe",
            "Tocantins"
        )
        val text = remember { mutableStateOf("") }
        val isOpen = remember { mutableStateOf(false) }
        val openCloseOfDropDownList: (Boolean) -> Unit = {
            isOpen.value = it
        }
        val userSelectedString: (String) -> Unit = {
            text.value = it
        }
        Box {
            Column {
                OutlinedTextField(
                    value = text.value,
                    onValueChange = { text.value = it },
                    modifier = Modifier.fillMaxWidth()
                )
                DropDownList(
                    open = isOpen.value,
                    list = stateList,
                    openCloseOfDropDownList,
                    userSelectedString
                )
            }
            Spacer(
                modifier = Modifier
                    .matchParentSize()
                    .background(Color.Transparent)
                    .padding(10.dp)
                    .clickable(
                        onClick = { isOpen.value = true }
                    )
            )
        }
    }

    @Composable
    private fun GenderField() {
        Text("Gender")
        Row(
            Modifier
                .selectableGroup()
                .fillMaxWidth(),
        ) {
            RadioButtonWithLabel("Male", onClick = { })
            RadioButtonWithLabel("Female", onClick = { })
        }
    }

    @Composable
    private fun RadioButtonWithLabel(
        label: String,
        selected: Boolean = false,
        onClick: () -> Unit
    ) {
        RadioButton(selected = selected, onClick = onClick)
        Text(label)
    }

    @Composable
    private fun CheckboxWithLabel(
        label: String,
        checked: Boolean = false,
        onChange: (Boolean) -> Unit
    ) {
        Row {
            Checkbox(
                checked = checked,
                onCheckedChange = onChange
            )
            Text(label)
        }
    }

    @Composable
    private fun TextFieldWithLabel(
        label: String = "",
        onChange: (String) -> Unit = { },
        defaultValue: String = "",
        placeholder: @Composable (() -> Unit)? = null
    ) {
        Text(label)
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = defaultValue,
            onValueChange = onChange,
            placeholder = placeholder
        )
    }
}
