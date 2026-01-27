// src/services/storage/userData.storage.ts

import type { UserData } from "../../types/UserData"

export const USER_DATA_KEY = (userId: string) =>
  `user:${userId}:data`

export function getUserData(userId: string): UserData {
  const today = new Date().toISOString().split("T")[0]
  const key = USER_DATA_KEY(userId)

  const stored = localStorage.getItem(key)

  if (!stored) {
    const newData: UserData = {
      id: userId,
      lastLogin: today,
      lastReviewDate: today,
      reviewsToday: 0,
    }

    localStorage.setItem(key, JSON.stringify(newData))
    return newData
  }

  const parsed: UserData = JSON.parse(stored)

  if (parsed.lastReviewDate !== today) {
    parsed.lastReviewDate = today
    parsed.reviewsToday = 0
  }

  parsed.lastLogin = today

  localStorage.setItem(key, JSON.stringify(parsed))
  return parsed
}

