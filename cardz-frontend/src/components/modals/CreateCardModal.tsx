import { useState } from "react";
import { useParams } from "react-router";
import type { CreateCardData } from "../../types/Card";
import { createCard } from "../../services/card.service";
import Swal from "sweetalert2";

type Props = {
    open: boolean;
    onClose: () => void;
}

export default function CreateDeckModal({ open, onClose }: Props) {
    const [front, setFront] = useState('');
    const [back, setBack] = useState('');
    const { deckId } = useParams();

    if (!open) return null;

    async function create() {
        const data = { front, back, deckId };
        const response = await createCard(data as CreateCardData);
        if (response.status === 201) {
            setFront('');
            setBack('');
            onClose();
        } else {
            Swal.fire({
                icon: 'error',
                title: 'Erro ao criar carta',
                text: "Tente novamente mais tarde.",
            });
        }
    }

    return (
        <div className="fixed inset-0 z-50 flex items-center justify-center">
        {/* Overlay */}
        <div
            className="absolute inset-0 bg-black/60"
            onClick={onClose}
        />

        {/* Modal */}
        <div className="
            relative z-10 w-full max-w-md
            bg-slate-800 rounded-2xl p-6 shadow-xl
        ">
            <h2 className="text-xl font-bold mb-4">
            Criar Nova Carta
            </h2>

            <div className="space-y-4">
            <div>
                <label className="text-sm text-slate-400">
                Frente
                </label>
                <input
                type="text"
                value={front}
                onChange={(e) => setFront(e.target.value)}
                placeholder="Ex: Please"
                className="
                    w-full mt-1 px-4 py-3 rounded-xl
                    bg-slate-900 text-white
                    border border-slate-700
                    focus:outline-none focus:ring-2 focus:ring-sky-500
                "
                />
            </div>

            <div>
                <label className="text-sm text-slate-400">
                Costas
                </label>
                <input
                type="text"
                value={back}
                onChange={(e) => setBack(e.target.value)}
                placeholder="Ex: Por favor"
                className="
                    w-full mt-1 px-4 py-3 rounded-xl
                    bg-slate-900 text-white
                    border border-slate-700
                    focus:outline-none focus:ring-2 focus:ring-sky-500
                "
                />
            </div>

            <div className="flex gap-3 pt-2">
                <button
                onClick={onClose}
                className="
                    flex-1 py-3 rounded-xl
                    bg-slate-700 hover:bg-slate-600
                "
                >
                Cancelar
                </button>

                <button
                className="
                    flex-1 py-3 rounded-xl font-semibold
                    bg-sky-500 text-slate-900 hover:bg-sky-400
                "
                onClick={create}
                >
                Criar
                </button>
            </div>
            </div>
        </div>
        </div>
    );
}