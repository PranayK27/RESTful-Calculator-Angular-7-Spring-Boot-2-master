
/**
 * This object represent the result obtained from webservice
 */
export class Result{
    public result: number;
    public error: boolean;
    public message: string;

    constructor(result: number, error: boolean, message ?: string){
        this.result = result;
        this.error = error;
        this.message = message;
    }
}
