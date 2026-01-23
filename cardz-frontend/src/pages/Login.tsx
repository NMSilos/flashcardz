import { useState } from "react";
import { loginRequest } from "../services/auth.service";
import Swal from "sweetalert2";
import 'sweetalert2/themes/bulma.css'
import { useNavigate } from "react-router";

export default function Login() {

  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  const navigate = useNavigate();

  async function login (e: React.FormEvent) {
    e.preventDefault();

    try {
      const data = { email, password };
      const response = await loginRequest(data);
      const { token } = response.data;
      
      localStorage.setItem('token', token);
      navigate('/dashboard');
    } 
    catch (error) {
      Swal.fire({
        icon: 'error',
        title: 'Erro ao fazer login',
        text: "Verifique suas credenciais e tente novamente.",
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
          Entre para continuar estudando
        </p>

        <form className="space-y-4" onSubmit={login}>
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

          <button
            type="submit"
            className="
              w-full py-3 rounded-xl
              bg-sky-500 text-slate-900 font-semibold
              hover:bg-sky-400 transition cursor-pointer
              active: bg-sky-600
            "
          >
            Entrar
          </button>
        </form>

        <div className="mt-6 text-center">
          <span className="text-slate-400 text-sm">
            Não tem conta?
          </span>
          <button className="ml-2 text-sm text-sky-500 hover:underline cursor-pointer">
            Criar conta
          </button>
        </div>
      </div>
    </div>
  )
}


