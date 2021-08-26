package vander.gabriel.cadastro

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import vander.gabriel.cadastro.ui.theme.CadastroTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CadastroTheme {
                MyApp()
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
    fun MyApp() {
        var fullName by remember { mutableStateOf("") }
        var phoneNumber by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var signedUpForNewsletter by remember { mutableStateOf(false) }
        var gender by remember { mutableStateOf("") }
        var city by remember { mutableStateOf("") }
        var state by remember { mutableStateOf("") }

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
                TextFieldWithLabel(
                    label = "Full name",
                    value = fullName,
                    onChange = {
                        fullName = it
                    },
                )
                Spacer(modifier = Modifier.size(10.dp))
                TextFieldWithLabel(
                    label = "Phone number",
                    placeholder = { Text("Ex.: 12-93456-7890") },
                    value = phoneNumber,
                    onChange = {
                        phoneNumber = it
                    },
                )
                Spacer(modifier = Modifier.size(10.dp))
                TextFieldWithLabel(
                    label = "Email",
                    value = email,
                    onChange = {
                        email = it
                    },
                )
                Spacer(modifier = Modifier.size(10.dp))
                CheckboxWithLabel(
                    label = "Signup for newsletter",
                    checked = signedUpForNewsletter,
                    onChange = {
                        signedUpForNewsletter = it
                    },
                )
                Spacer(modifier = Modifier.size(10.dp))
                GenderField(value = gender, onGenderSelected = { gender = it })
                Spacer(modifier = Modifier.size(10.dp))
                TextFieldWithLabel(
                    label = "City",
                    value = city,
                    onChange = {
                        city = it
                    },
                )
                Spacer(modifier = Modifier.size(10.dp))
                Text("State")
                SateSelection(
                    value = state,
                    onValueSelected = { state = it },
                )
                Spacer(modifier = Modifier.size(10.dp))
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(onClick = { }) {
                        Text("Clear")
                    }
                    Button(onClick = {
                        val form = Form(
                            fullName = fullName,
                            phoneNumber = phoneNumber,
                            email = email,
                            signedUpForNewsletter = signedUpForNewsletter,
                            gender = gender,
                            city = city,
                            state = state
                        )
                        Toast.makeText(
                            applicationContext,
                            form.toString(),
                            Toast.LENGTH_LONG
                        ).show()
                    }) {
                        Text("Save")
                    }
                }
            }
        }
    }

    @Composable
    fun SateSelection(value: String, onValueSelected: (String) -> Unit) {
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
        val isOpen = remember { mutableStateOf(false) }
        val openCloseOfDropDownList: (Boolean) -> Unit = {
            isOpen.value = it
        }

        Box {
            Column {
                OutlinedTextField(
                    value = value,
                    onValueChange = { },
                    trailingIcon = {
                        Icon(
                            Icons.Filled.ArrowDropDown,
                            contentDescription = "contentDescription",
                            Modifier.rotate(if (isOpen.value) 180f else 0f)
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                DropDownList(
                    open = isOpen.value,
                    list = stateList,
                    openCloseOfDropDownList,
                    onValueSelected
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
    private fun GenderField(value: String, onGenderSelected: (String) -> Unit) {
        Text("Gender")
        Row(
            Modifier
                .selectableGroup()
                .fillMaxWidth(),
        ) {
            RadioButtonWithLabel(
                label = "Male",
                value = value,
                onClick = { onGenderSelected("Male") },
            )
            RadioButtonWithLabel(label = "Female", onClick = { onGenderSelected("Female") })
        }
    }

    @Composable
    private fun RadioButtonWithLabel(
        label: String,
        value: String = "",
        onClick: () -> Unit
    ) {
        RadioButton(
            selected = label == value,
            onClick = onClick,
        )
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
        value: String = "",
        placeholder: @Composable (() -> Unit)? = null
    ) {
        Text(label)
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = value,
            onValueChange = onChange,
            placeholder = placeholder
        )
    }
}
