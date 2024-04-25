/* eslint-disable @typescript-eslint/no-explicit-any */
import firebase from 'firebase/app';
import 'firebase/firestore';

const config = {
  apiKey: 'Not working',
  authDomain: 'Not working',
  databaseURL: 'Not working',
  projectId: 'Not working',
  storageBucket: 'Not working',
  messagingSenderId: 'Not working',
  appId: 'Not working',
  measurementId: 'Not working',
};
if (!firebase.apps.length) {
  firebase.initializeApp(config);
}

// Make the helper types for updates:
type PathImpl<T, K extends keyof T> = K extends string
  ? T[K] extends Record<string, any>
    ? T[K] extends ArrayLike<any>
      ? K | `${K}.${PathImpl<T[K], Exclude<keyof T[K], keyof any[]>>}`
      : K | `${K}.${PathImpl<T[K], keyof T[K]>}`
    : K
  : never;
type Path<T> = PathImpl<T, keyof T> | keyof T;
type PathValue<T, P extends Path<T>> = P extends `${infer K}.${infer Rest}`
  ? K extends keyof T
    ? Rest extends Path<T[K]>
      ? PathValue<T[K], Rest>
      : never
    : never
  : P extends keyof T
    ? T[P]
    : never;

// eslint-disable-next-line @typescript-eslint/ban-types
export type UpdateData<T extends object> = Partial<{
  [TKey in Path<T>]: PathValue<T, TKey>;
}>;

const COLLECTIONS = {
  ROOMS: 'rooms',
  CALLEECANDIDATES: 'calleeCandidates',
  CALLERCANDIDATES: 'callerCandidates',
};

export type RoomField = {
  name: string;
  sdp: string;
  type: RTCSdpType;
};
export type Room = {
  offer: RoomField;
  answer?: RoomField;
};

const converter = <T>() => ({
  toFirestore: (data: Partial<T>) => data,
  fromFirestore: (snap: firebase.firestore.QueryDocumentSnapshot) => snap.data() as T,
});
const dataPoint = <T>(collectionPath: string) => firebase.firestore().collection(collectionPath).withConverter(converter<T>());
const db = {
  rooms: dataPoint<Room>(COLLECTIONS.ROOMS),
};

export { db, COLLECTIONS };
export default firebase;
