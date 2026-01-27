import { useEffect, useState } from "react";
import AppLayout from "../layouts/AppLayout";
import { getPayloadFromToken } from "../services/auth.service";
import { getUserByIdRequest } from "../services/user.service";
import type { Deck } from "../types/Deck";
import { getUserData } from "../services/storage/userData.service";

export default function Dashboard() {

  const [user, setUser] = useState(null);
  const [decks, setDecks] = useState<Deck[]>([]);
  const [reviews, setReviews] = useState(0);
  const [username, setUsername] = useState("Usuário");

  async function fetchUserData() {
    const payload = getPayloadFromToken();
    const response = await getUserByIdRequest(payload!.id);
    setUser(response.data);
    setDecks(response.data.decks);
  }

  const totalCards = decks.reduce((total, deck) => {
    return total + deck.cards.length
  }, 0)


  useEffect(() => {
    const payload = getPayloadFromToken();
    if (payload !== null) {
      setUsername(payload.username);
    }
    fetchUserData();

    const userData = getUserData(payload!.id);
    setReviews(userData.reviewsToday);
  }, []);

  return (
    <AppLayout>
      {/* Header */}
      <div className="mb-8">
        <h1 className="text-2xl font-bold">
          Bem vindo, {username}!
        </h1>
        <p className="text-slate-400">
          Acompanhe seu progresso de estudos 📚
        </p>
      </div>

      {/* Stats */}
      <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-6 mb-8">
        <StatCard title="Decks" value={`${decks.length}`} />
        <StatCard title="Cartas" value={`${totalCards.toString()}`} />
        <StatCard title="Revisões hoje" value={`${reviews}`} />
        <StatCard title="Sequência" value="7 dias" />
      </div>

      {/* Main grid */}
      <div className="grid grid-cols-1 lg:grid-cols-3 gap-6">
        {/* Decks */}
        <div className="lg:col-span-2 bg-slate-800 rounded-2xl p-6 shadow">
          <h2 className="text-lg font-semibold mb-4">
            Meus Decks
          </h2>

          <div className="space-y-3 max-h-[420px] overflow-y-auto pr-2">
            {decks.map((deck) => (
              <DeckItem key={deck.id} name={deck.name} cards={deck.cards.length} />
            ))}
          </div>
        </div>

        {/* Actions */}
        <div className="bg-slate-800 rounded-2xl p-6 shadow">
          <h2 className="text-lg font-semibold mb-4">
            Ações rápidas
          </h2>

          <div className="space-y-3">
            <ActionButton label="Estudar agora" />
            <ActionButton label="Criar novo deck" secondary />
          </div>
        </div>
      </div>
    </AppLayout>
  );
}

/* ================= COMPONENTES ================= */

function StatCard({ title, value }: { title: string; value: string }) {
  return (
    <div className="
      bg-slate-800 rounded-2xl p-6 shadow
      flex flex-col gap-2
    ">
      <span className="text-slate-400 text-sm">
        {title}
      </span>
      <span className="text-2xl font-bold text-sky-400">
        {value}
      </span>
    </div>
  );
}

function DeckItem({ name, cards }: { name: string; cards: number }) {
  return (
    <div className="
      flex items-center justify-between
      p-4 rounded-xl
      bg-slate-900 hover:bg-slate-700
      transition cursor-pointer
    ">
      <div>
        <h3 className="font-medium">
          {name}
        </h3>
        <p className="text-slate-400 text-sm">
          {cards} cartas
        </p>
      </div>

      <span className="text-sky-400 font-medium">
        Abrir →
      </span>
    </div>
  );
}

function ActionButton({
  label,
  secondary = false,
}: {
  label: string;
  secondary?: boolean;
}) {
  return (
    <button
      className={`
        w-full py-3 rounded-xl font-semibold transition
        ${secondary
          ? "bg-slate-700 text-slate-100 hover:bg-slate-600"
          : "bg-sky-500 text-slate-900 hover:bg-sky-400"
        }
      `}
    >
      {label}
    </button>
  );
}
