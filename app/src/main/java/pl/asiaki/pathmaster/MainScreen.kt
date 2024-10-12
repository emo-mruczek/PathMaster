package pl.asiaki.pathmaster

@Composable
fun FormSelectorButtons() {
    var showContent: Int by remember { mutableIntStateOf(0) }
    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        TreeHeader("Dodaj eko-akcję")
        Text("Rodzaj Akcji")
        // Image (chmura)
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(onClick = { showContent = 1 }) {
                Image(
                    painter = painterResource(R.mipmap.mieso_foreground),
                    contentDescription = "TODO",
                    modifier = Modifier.width(70.dp)
                )
            }
            Button(onClick = { showContent = 2 }) {
                Image(
                    painter = painterResource(R.mipmap.samochod_foreground),
                    contentDescription = "TODO",
                    modifier = Modifier.width(70.dp)
                )
            }
            Button(onClick = { showContent = 3 }) {
                Image(
                    painter = painterResource(R.mipmap.pojazdtorowy_foreground),
                    contentDescription = "TODO",
                    modifier = Modifier.width(70.dp)
                )
            }
        }
        Row(Modifier.fillMaxWidth().padding(0.dp, 0.dp, 0.dp, 50.dp), horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(onClick = { showContent = 4 }) {
                Image(
                    painter = painterResource(R.mipmap.wozek_foreground),
                    contentDescription = "TODO",
                    modifier = Modifier.width(70.dp)
                )
            }
            Button(onClick = { showContent = 5 }) {
                Image(
                    painter = painterResource(R.mipmap.atleta_foreground),
                    contentDescription = "TODO",
                    modifier = Modifier.width(70.dp)
                )
            }
            Button(onClick = { showContent = 6 }) {
                Image(
                    painter = painterResource(R.mipmap.piorun_foreground),
                    contentDescription = "TODO",
                    modifier = Modifier.width(70.dp)
                )
            }
        }
        if (showContent != 0)
            FormSelector(showContent)
    }
}

@Composable
fun FormSelector(whatShow: Int) {
    var note: String by remember { mutableStateOf("") }
    when(whatShow) {
        1 -> {
            TextField(value = note, onValueChange = { note = it }, label = { Text("Zjedzona potrawa") })
            Button(onClick = {send(note); note = ""}, modifier = Modifier.fillMaxWidth(0.3f)) {
                Text("Wyslij")
            }
        }
        2 -> {
            TextField(keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), value = note, onValueChange = { note = it }, label = { Text("Przejechany dystans (km)") })
            Button(onClick = {send(note); note = ""}, modifier = Modifier.fillMaxWidth(0.3f)) {
                Text("Wyslij")
            }
        }
        3 -> {
            TextField(keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), value = note, onValueChange = { note = it }, label = { Text("Przejechany dystans (km)") })
            Button(onClick = {send(note); note = ""}, modifier = Modifier.fillMaxWidth(0.3f)) {
                Text("Wyslij")
            }
        }
        4 -> {
            TextField(value = note, onValueChange = { note = it }, label = { Text("Zakupy") })
            Button(onClick = {send(note); note = ""}, modifier = Modifier.fillMaxWidth(0.3f)) {
                Text("Wyslij")
            }
        }
        5 -> {
            TextField(keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), value = note, onValueChange = { note = it }, label = { Text("Przebyty dystans (km)") })
            Button(onClick = {send(note); note = ""}, modifier = Modifier.fillMaxWidth(0.3f)) {
                Text("Wyslij")
            }
        }
        6 -> {
            TextField(value = note, onValueChange = { note = it }, label = { Text("Inne") })
            Button(onClick = {send(note); note = ""}, modifier = Modifier.fillMaxWidth(0.3f)) {
                Text("Wyslij")
            }
        }
        else -> {
            Text("Błąd, to nigdy nie powinno się pojawić")
        }
    }
}
