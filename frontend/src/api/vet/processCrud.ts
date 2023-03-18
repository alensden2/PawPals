import { Vet } from "@src/types"
import { registerVetApiCall } from "./crud";

export const registerVet = async (vet: Omit<Vet,"vetUserId">) => {
    const response = await registerVetApiCall(vet);
    return response;
}