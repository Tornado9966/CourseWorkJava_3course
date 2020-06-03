import * as bcrypt from 'bcryptjs';
import has = Reflect.has;

export class HashBcrypt {

  static hash(value: string, rounds?: number): string {
    const salt = bcrypt.genSaltSync(rounds ? rounds : 10);
    return bcrypt.hashSync(value, salt);
  }
  static compare(pass: string, hash: string): boolean {
    return bcrypt.compareSync(pass, hash);
  }
}
