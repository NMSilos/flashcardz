import { useState } from "react";
import { loginRequest } from "../services/auth.service";

export default function Login() {

  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  async function login (e: React.FormEvent) {
    e.preventDefault();
    const data = { email, password };
    
    const response = await loginRequest(data);
    if (response.status === 200) {
      const { token } = response.data;
      localStorage.setItem('token', token);
    }
  }

  return (
    <div className="min-h-screen bg-zinc-900 flex items-center justify-center">
      <div className="w-full max-w-md bg-zinc-800 rounded-2xl shadow-xl p-8">
        <h1 className="text-3xl font-bold text-white text-center mb-2">
          Flashcardz
        </h1>

        <p className="text-zinc-400 text-center mb-8">
          Entre para continuar estudando
        </p>

        <form className="space-y-4" onSubmit={login}>
          <div>
            <label className="block text-sm text-zinc-400 mb-1">
              Email
            </label>
            <input
              type="email"
              placeholder="seu@email.com"
              className="
                w-full px-4 py-3 rounded-xl
                bg-zinc-900 text-white
                border border-zinc-700
                focus:outline-none focus:ring-2 focus:ring-indigo-600
              "
              value={email}
              onChange={(e) => setEmail(e.target.value)}
            />
          </div>

          <div>
            <label className="block text-sm text-zinc-400 mb-1">
              Senha
            </label>
            <input
              type="password"
              placeholder="••••••••"
              className="
                w-full px-4 py-3 rounded-xl
                bg-zinc-900 text-white
                border border-zinc-700
                focus:outline-none focus:ring-2 focus:ring-indigo-600
              "
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />
          </div>

          <button
            type="submit"
            className="
              w-full py-3 rounded-xl
              bg-indigo-600 text-white font-semibold
              hover:bg-indigo-500 transition
            "
          >
            Entrar
          </button>
        </form>

        <div className="mt-6 text-center">
          <span className="text-zinc-400 text-sm">
            Não tem conta?
          </span>
          <button className="ml-2 text-sm text-indigo-500 hover:underline">
            Criar conta
          </button>
        </div>
      </div>
    </div>
  )
}


