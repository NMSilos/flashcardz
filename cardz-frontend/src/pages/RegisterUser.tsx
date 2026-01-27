import { useState } from "react";
import { registerRequest } from "../services/auth.service";
import Swal from "sweetalert2";
import 'sweetalert2/themes/bulma.css'
import { useNavigate } from "react-router";

export default function RegisterUser() {

  const [username, setUsername] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');

  const navigate = useNavigate();

  async function register (e: React.FormEvent) {
    e.preventDefault();

    if (password !== confirmPassword) {
      Swal.fire({
        icon: 'error',
        title: 'Senhas não coincidem',
        text: "Verifique suas credenciais e tente novamente.",
      });
      return;
    }

    try {
      const data = { username, email, password };
      const response = await registerRequest(data);
      if (response.status === 201) {
        navigate('/');
      }
    } catch (error) {
      Swal.fire({
        icon: 'error',
        title: 'Erro ao registar usuário',
        text: "Verifique os dados informados e tente novamente.",
      });
    }
  }

  return (
    <div className="min-h-screen bg-slate-900 flex items-center justify-center">
      <div className="w-full max-w-md bg-slate-800 rounded-2xl shadow-xl p-8 border-slate-600 border">
        <h1 className="text-3xl font-bold text-slate-100 text-center mb-2">
          Flashcardz
        </h1>

        <p className="text-slate-400 text-center mb-8">
          Registre-se para começar!
        </p>

        <form className="space-y-4" onSubmit={register}>
          <div>
            <label className="block text-sm text-slate-400 mb-1">
              Username
            </label>
            <input
              type="username"
              placeholder="Insira seu username"
              className="
                w-full px-4 py-3 rounded-xl
                bg-slate-700 text-slate-100
                border border-slate-700
                placeholder:text-slate-500
                focus:outline-none focus:ring-2 focus:ring-slate-500
              "
              value={username}
              onChange={(e) => setUsername(e.target.value)}
            />
          </div>

          <div>
            <label className="block text-sm text-slate-400 mb-1">
              Email
            </label>
            <input
              type="email"
              placeholder="seu@email.com"
              className="
                w-full px-4 py-3 rounded-xl
                bg-slate-700 text-slate-100
                border border-slate-700
                placeholder:text-slate-500
                focus:outline-none focus:ring-2 focus:ring-slate-500
              "
              value={email}
              onChange={(e) => setEmail(e.target.value)}
            />
          </div>

          <div>
            <label className="block text-sm text-slate-400 mb-1">
              Senha
            </label>
            <input
              type="password"
              placeholder="••••••••"
              className="
                w-full px-4 py-3 rounded-xl
                bg-slate-700 text-slate-100
                border border-slate-700
                placeholder:text-slate-500
                focus:outline-none focus:ring-2 focus:ring-slate-500
              "
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />
          </div>

          <div>
            <label className="block text-sm text-slate-400 mb-1">
              Repita sua senha
            </label>
            <input
              type="password"
              placeholder="••••••••"
              className="
                w-full px-4 py-3 rounded-xl
                bg-slate-700 text-slate-100
                border border-slate-700
                placeholder:text-slate-500
                focus:outline-none focus:ring-2 focus:ring-slate-500
              "
              value={confirmPassword}
              onChange={(e) => setConfirmPassword(e.target.value)}
            />
          </div>

          <button
            type="submit"
            className="
              w-full py-3 mt-4 rounded-xl
              bg-sky-500 text-slate-900 font-semibold
              hover:bg-sky-400 transition cursor-pointer
              active: bg-sky-600
            "
          >
            Registrar
          </button>
        </form>
      </div>
    </div>
  )
}


