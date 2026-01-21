export default function Login() {
  return (
    <div className="min-h-screen bg-zinc-900 flex items-center justify-center">
      <div className="w-full max-w-md bg-zinc-800 rounded-2xl shadow-xl p-8">
        <h1 className="text-3xl font-bold text-white text-center mb-2">
          Flashcardz
        </h1>

        <p className="text-zinc-400 text-center mb-8">
          Entre para continuar estudando
        </p>

        <form className="space-y-4">
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


