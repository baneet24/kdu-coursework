export interface RoomType {
    id:           number;
    name:         string;
    costPerNight: number;
    currency:     Currency;
    addOns:       AddOn[];
    isselected: boolean; 
}

export interface AddOn {
    name:     string;
    cost:     string;
    currency: Currency;
}

export enum Currency {
    Inr = "INR",
}