import { Vet } from "@src/types";

export interface RegisterVetApiInputType extends Omit<Vet, "vetUserId">{

}

export interface RegisterVetApiResponseType extends Vet{
    
}