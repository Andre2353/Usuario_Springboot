const API_URL = "http://localhost:8080/usuario";
const LOGIN_URL = "http://localhost:8080/login";


// ==========================================
// LOGIN
// ==========================================

const formLogin = document.getElementById("formLogin");

formLogin.addEventListener("submit", async function (event) {

    event.preventDefault();

    const email = document.getElementById("loginEmail").value;
    const senha = document.getElementById("loginSenha").value;

    const dadosLogin = {
        email: email,
        senha: senha
    };

    try {

        const response = await fetch(LOGIN_URL, {

            method: "POST",

            headers: {
                "Content-Type": "application/json"
            },

            body: JSON.stringify(dadosLogin)

        });

        const resultado = await response.text();

        if (!response.ok) {
            throw new Error(resultado);
        }

        document.getElementById("mensagemLogin").textContent =
            "Login realizado com sucesso!";

        document.getElementById("mensagemLogin").className =
            "sucesso";

        console.log("Resposta do login:", resultado);

    } catch (error) {

        console.error("Erro no login:", error);

        document.getElementById("mensagemLogin").textContent =
            "E-mail ou senha inválidos.";

        document.getElementById("mensagemLogin").className =
            "erro";

    }

});


// ==========================================
// CADASTRAR USUÁRIO
// ==========================================

const formUsuario = document.getElementById("formUsuario");

formUsuario.addEventListener("submit", async function (event) {

    event.preventDefault();

    const nome = document.getElementById("nome").value;
    const email = document.getElementById("email").value;
    const senha = document.getElementById("senha").value;

    const usuario = {
        nome: nome,
        email: email,
        senha: senha
    };

    try {

        const response = await fetch(API_URL, {

            method: "POST",

            headers: {
                "Content-Type": "application/json"
            },

            body: JSON.stringify(usuario)

        });

        const resultado = await response.text();

        if (!response.ok) {
            throw new Error(resultado);
        }

        document.getElementById("mensagem").textContent =
            "Usuário cadastrado com sucesso!";

        document.getElementById("mensagem").className =
            "sucesso";

        formUsuario.reset();

        listarUsuarios();

    } catch (error) {

        console.error("Erro ao cadastrar:", error);

        document.getElementById("mensagem").textContent =
            "Erro ao cadastrar usuário.";

        document.getElementById("mensagem").className =
            "erro";

    }

});


// ==========================================
// LISTAR USUÁRIOS
// ==========================================

async function listarUsuarios() {

    const tabela = document.getElementById("tabelaUsuarios");

    tabela.innerHTML = "";

    try {

        const response = await fetch(API_URL);

        if (!response.ok) {
            throw new Error("Erro ao buscar usuários");
        }

        const usuarios = await response.json();

        usuarios.forEach(usuario => {

            const linha = document.createElement("tr");

            linha.innerHTML = `

                <td>${usuario.id}</td>

                <td>${usuario.nome}</td>

                <td>${usuario.email}</td>

                <td>${usuario.dtCriacao}</td>

                <td>

                    <button
                        type="button"
                        onclick="editarUsuario(${usuario.id})">
                        Editar
                    </button>

                    <button
                        type="button"
                        class="excluir"
                        onclick="deletarUsuario(${usuario.id})">
                        Excluir
                    </button>

                </td>

            `;

            tabela.appendChild(linha);

        });

    } catch (error) {

        console.error("Erro ao listar usuários:", error);

        tabela.innerHTML = `

            <tr>

                <td colspan="5">

                    Não foi possível conectar ao Back-end.

                </td>

            </tr>

        `;

    }

}


// ==========================================
// BUSCAR USUÁRIO POR ID
// ==========================================

document
    .getElementById("btnBuscar")
    .addEventListener("click", buscarUsuario);


async function buscarUsuario() {

    const id = document.getElementById("idBusca").value;

    if (!id) {

        alert("Digite um ID.");

        return;
    }

    try {

        const response = await fetch(
            `${API_URL}/${id}`
        );

        if (!response.ok) {

            throw new Error("Usuário não encontrado");

        }

        const usuario = await response.json();

        document.getElementById("resultadoBusca").innerHTML = `

            <div class="usuario">

                <strong>ID:</strong>
                ${usuario.id}

                <br>

                <strong>Nome:</strong>
                ${usuario.nome}

                <br>

                <strong>E-mail:</strong>
                ${usuario.email}

                <br>

                <strong>Data de criação:</strong>
                ${usuario.dtCriacao}

            </div>

        `;

    } catch (error) {

        console.error("Erro ao buscar usuário:", error);

        document.getElementById("resultadoBusca").innerHTML = `

            <p class="erro">

                Usuário não encontrado.

            </p>

        `;

    }

}


// ==========================================
// EDITAR USUÁRIO
// ==========================================

async function editarUsuario(id) {

    console.log("Editando usuário:", id);

    try {

        const response = await fetch(
            `${API_URL}/${id}`
        );

        if (!response.ok) {

            throw new Error(
                "Usuário não encontrado"
            );

        }

        const usuario = await response.json();

        document.getElementById("idEdicao").value =
            usuario.id;

        document.getElementById("nomeEdicao").value =
            usuario.nome;

        document.getElementById("emailEdicao").value =
            usuario.email;

        document.getElementById("senhaEdicao").value =
            "";

        document.getElementById("secaoEdicao").style.display =
            "block";

        document.getElementById("secaoEdicao").scrollIntoView({
            behavior: "smooth"
        });

    } catch (error) {

        console.error(
            "Erro ao carregar usuário:",
            error
        );

        alert(
            "Não foi possível carregar o usuário."
        );

    }

}


// ==========================================
// ATUALIZAR USUÁRIO - PUT
// ==========================================

const formEdicao = document.getElementById("formEdicao");

formEdicao.addEventListener("submit", async function (event) {

    event.preventDefault();

    const id =
        document.getElementById("idEdicao").value;

    const usuario = {

        nome:
            document.getElementById("nomeEdicao").value,

        email:
            document.getElementById("emailEdicao").value,

        senha:
            document.getElementById("senhaEdicao").value

    };

    try {

        const response = await fetch(
            `${API_URL}/${id}`,
            {

                method: "PUT",

                headers: {
                    "Content-Type": "application/json"
                },

                body: JSON.stringify(usuario)

            }
        );

        const resultado = await response.text();

        if (!response.ok) {

            throw new Error(resultado);

        }

        document.getElementById("mensagemEdicao").textContent =
            "Usuário atualizado com sucesso!";

        document.getElementById("mensagemEdicao").className =
            "sucesso";

        listarUsuarios();

    } catch (error) {

        console.error(
            "Erro ao atualizar usuário:",
            error
        );

        document.getElementById("mensagemEdicao").textContent =
            "Erro ao atualizar usuário.";

        document.getElementById("mensagemEdicao").className =
            "erro";

    }

});


// ==========================================
// CANCELAR EDIÇÃO
// ==========================================

document
    .getElementById("btnCancelarEdicao")
    .addEventListener("click", function () {

        document.getElementById("secaoEdicao").style.display =
            "none";

        document.getElementById("formEdicao").reset();

        document.getElementById("mensagemEdicao").textContent =
            "";

    });


// ==========================================
// DELETAR USUÁRIO
// ==========================================

async function deletarUsuario(id) {

    const confirmar = confirm(
        `Deseja excluir o usuário ${id}?`
    );

    if (!confirmar) {

        return;

    }

    try {

        const response = await fetch(
            `${API_URL}/${id}`,
            {
                method: "DELETE"
            }
        );

        const resultado = await response.text();

        if (!response.ok) {

            throw new Error(resultado);

        }

        alert(
            "Usuário excluído com sucesso!"
        );

        listarUsuarios();

    } catch (error) {

        console.error(
            "Erro ao excluir usuário:",
            error
        );

        alert(
            "Erro ao excluir usuário."
        );

    }

}


// ==========================================
// ATUALIZAR LISTA
// ==========================================

document
    .getElementById("btnAtualizar")
    .addEventListener("click", listarUsuarios);


// ==========================================
// CARREGAR USUÁRIOS AO ABRIR A PÁGINA
// ==========================================

listarUsuarios();